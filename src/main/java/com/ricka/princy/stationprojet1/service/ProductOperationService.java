package com.ricka.princy.stationprojet1.service;

import com.ricka.princy.stationprojet1.exception.BadRequestException;
import com.ricka.princy.stationprojet1.exception.NotFoundException;
import com.ricka.princy.stationprojet1.entity.*;
import com.ricka.princy.stationprojet1.repository.ProductOperationRepository;
import com.ricka.princy.stationprojet1.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductOperationService {
    private final ProductOperationRepository productOperationRepository;
    private final ProductRepository productRepository;
    private final ProductTemplateService productTemplateService;
    private static final BigDecimal MAX_SALE_QUANTITY = BigDecimal.valueOf(200);

    public List<ProductOperation> getAllProductOperations(){
        try {
            return productOperationRepository.findAll().stream().map(this::mapProductStockToOperationDatetimeStock).collect(Collectors.toList());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ProductOperation saveOrUpdate(ProductOperation productOperation){
        try {
            productOperationRepository.saveOrUpdate(productOperation);
            return productOperationRepository.findById(productOperation.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ProductOperation getById(String productOperationId){
        try {
            ProductOperation productOperation = productOperationRepository.findById(productOperationId);
            if(productOperation == null){
                throw new NotFoundException(String.format("ProductOperation with {id: %s} is not found", productOperationId));
            }
            return this.mapProductStockToOperationDatetimeStock(productOperation);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ProductOperation> getByStationId(String stationId, Instant from, Instant to, ProductOperationType type){
        try {
            return productOperationRepository.findByStationId(stationId, from, to, type)
                    .stream()
                    .map(this::mapProductStockToOperationDatetimeStock)
                    .collect(Collectors.toList());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ProductOperation> getByProductId(String productId){
        try {
            return productOperationRepository.findByProductId(productId)
                    .stream()
                    .map(this::mapProductStockToOperationDatetimeStock)
                    .collect(Collectors.toList());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ProductOperation mapProductStockToOperationDatetimeStock(ProductOperation operation){
        Product product = operation.getProduct();
        try {
            product.setStock(productRepository.getStockInDateByProductId(product, operation.getOperationDatetime()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        operation.setProduct(product);
        return operation;
    }

    public void verifySaleProductOperationQuantity(BigDecimal quantity, BigDecimal stock){
        if(quantity.compareTo(MAX_SALE_QUANTITY) >= 0){
            throw new BadRequestException(String.format("Max sale quantity is %s", MAX_SALE_QUANTITY));
        }
        if(stock.compareTo(quantity) < 0){
            throw new BadRequestException(String.format("Insufficient stock. Remaining stock: %s" , stock));
        }
    }

    public ProductOperation doOperations(ProductOperation mappedOperation){
        try {
            ProductOperation oldOperation;
            oldOperation = productOperationRepository.findById(mappedOperation.getId());
            if( oldOperation != null)
                return oldOperation;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(mappedOperation.getQuantity().compareTo(BigDecimal.ZERO) <= 0){
            throw new BadRequestException("Operation quantity must be greater than 0");
        }else if(mappedOperation.getType().equals(ProductOperationType.PROCUREMENT)){
            return this.saveOrUpdate(mappedOperation);
        }

        this.verifySaleProductOperationQuantity(mappedOperation.getQuantity(), mappedOperation.getProduct().getStock());
        return this.saveOrUpdate(mappedOperation);
    }

    private static OperationStatement getCurrentOperationStatement(ProductOperation operation, OperationStatement currentOperationStatement) {
        return currentOperationStatement != null ? currentOperationStatement : new OperationStatement(
            operation.getProduct().getProductTemplate().getName(),
            BigDecimal.ZERO,
            BigDecimal.ZERO,
            BigDecimal.ZERO
        );
    }

    public List<OperationStatementValues> getOperationStatementByIdPerDay(String stationId, Instant from, Instant to){
        try {
            List<ProductOperation> productOperations = productOperationRepository.findByStationId(stationId, from, to, null);
            List<OperationStatementValues> operationStatementValues = new ArrayList<>();

            Instant currentDay = from.truncatedTo(ChronoUnit.DAYS);
            while (currentDay.isBefore(to.truncatedTo(ChronoUnit.DAYS))) {
                final Instant currentDate = currentDay;
                List<ProductOperation> currentOperations = productOperations.stream()
                    .filter(operation -> (
                        !operation.getOperationDatetime().isBefore(currentDate) &&
                        operation.getOperationDatetime().isBefore(currentDate.plus(1, ChronoUnit.DAYS))
                    )).toList();

                Map<String, OperationStatement> resultForCurrentDays = new HashMap<>();
                currentOperations.forEach(operation -> {
                    OperationStatement currentOperationStatement = resultForCurrentDays.getOrDefault(operation.getProduct().getProductTemplate().getName(), null);
                    boolean isSale = operation.getType().equals(ProductOperationType.SALE);
                    BigDecimal procurementToAdd = isSale ? BigDecimal.ZERO : operation.getQuantity();
                    BigDecimal saleToAdd = isSale ? operation.getQuantity() : BigDecimal.ZERO;

                    currentOperationStatement = getCurrentOperationStatement(operation, currentOperationStatement);
                    currentOperationStatement.setProcurementQuantity(currentOperationStatement.getProcurementQuantity().add(procurementToAdd));
                    currentOperationStatement.setSaleQuantity(currentOperationStatement.getSaleQuantity().add(saleToAdd));
                    currentOperationStatement.setRestQuantity(
                        currentOperationStatement.getProcurementQuantity().subtract(currentOperationStatement.getSaleQuantity())
                    );
                    resultForCurrentDays.put(operation.getProduct().getProductTemplate().getName(), currentOperationStatement);
                });

                operationStatementValues.add(new OperationStatementValues(currentDay, resultForCurrentDays.values().stream().toList()));
                currentDay = currentDay.plus(1, ChronoUnit.DAYS);
            }
            return operationStatementValues;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}