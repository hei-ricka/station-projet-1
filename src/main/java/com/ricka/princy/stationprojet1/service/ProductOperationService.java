package com.ricka.princy.stationprojet1.service;

import com.ricka.princy.stationprojet1.exception.BadRequestException;
import com.ricka.princy.stationprojet1.exception.NotFoundException;
import com.ricka.princy.stationprojet1.model.Product;
import com.ricka.princy.stationprojet1.model.ProductOperation;
import com.ricka.princy.stationprojet1.model.ProductOperationType;
import com.ricka.princy.stationprojet1.repository.ProductOperationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductOperationService {
    private final ProductOperationRepository productOperationRepository;
    private final ProductService productService;
    private static final BigDecimal MAX_SALE_QUANTITY = BigDecimal.valueOf(200);

    public List<ProductOperation> getAllProductOperations(){
        try {
            return productOperationRepository.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ProductOperation saveOrUpdate(ProductOperation productOperation){
        try {
            ProductOperation oldOperation = productOperationRepository.findById(productOperation.getId());
            if( oldOperation != null){
                return oldOperation;
            }
            productOperationRepository.saveOrUpdate(productOperation);
            return productOperationRepository.findById(productOperation.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ProductOperation> saveOrUpdateAll(List<ProductOperation> productOperations){
        List<ProductOperation> result = new ArrayList<>();
        for (ProductOperation productOperation : productOperations) {
            result.add(this.saveOrUpdate(productOperation));
        }
        return result;
    }

    public ProductOperation getById(String productOperationId){
        try {
            ProductOperation productOperation = productOperationRepository.findById(productOperationId);
            if(productOperation == null){
                throw new NotFoundException(String.format("ProductOperation with {id: %s} is not found", productOperationId));
            }
            return productOperation;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ProductOperation> getByProductId(String productId){
        try {
            return productOperationRepository.findByProductId(productId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ProductOperation doOperations(ProductOperation productOperation){
        Product product = productOperation.getProduct();
        product.setUpdatedAt(Instant.now());

        if(productOperation.getQuantity().compareTo(BigDecimal.ZERO) <= 0){
            throw new BadRequestException("Operation quantity must be greater than 0");
        }

        if(productOperation.getType().equals(ProductOperationType.PROCUREMENT)){
            product.setStock(productOperation.getQuantity().add(product.getStock()));
            productService.saveOrUpdate(product);
            return this.saveOrUpdate(productOperation);
        }

        this.verifySaleProductOperation(productOperation);
        product.setStock(product.getStock().add(productOperation.getQuantity().negate()));
        productService.saveOrUpdate(product);
        return this.saveOrUpdate(productOperation);
    }

    public void verifySaleProductOperation(ProductOperation productOperation){
        if(productOperation.getQuantity().compareTo(MAX_SALE_QUANTITY) >= 0){
            throw new BadRequestException(String.format("Max sale quantity is %s", MAX_SALE_QUANTITY));
        }

        if(productOperation.getProduct().getStock().compareTo(productOperation.getQuantity()) < 0){
            throw new BadRequestException(String.format("Insufficient stock. Remaining stock: %s" ,productOperation.getProduct().getStock()));
        }
    }
}
