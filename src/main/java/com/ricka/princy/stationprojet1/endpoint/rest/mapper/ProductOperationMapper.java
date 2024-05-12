package com.ricka.princy.stationprojet1.endpoint.rest.mapper;

import com.ricka.princy.stationprojet1.endpoint.rest.model.CreateProcurementOperation;
import com.ricka.princy.stationprojet1.endpoint.rest.model.CreateSaleOperation;
import com.ricka.princy.stationprojet1.exception.BadRequestException;
import com.ricka.princy.stationprojet1.entity.Product;
import com.ricka.princy.stationprojet1.entity.ProductOperation;
import com.ricka.princy.stationprojet1.entity.ProductOperationType;
import com.ricka.princy.stationprojet1.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@AllArgsConstructor
public class ProductOperationMapper {
    private final ProductService productService;

    public ProductOperation procurementToRest(CreateProcurementOperation productOperation) {
        return ProductOperation
                .builder()
                .id(productOperation.getId())
                .operationDatetime(productOperation.getOperationDatetime())
                .type(ProductOperationType.PROCUREMENT)
                .quantity(productOperation.getQuantity())
                .product(productService.getById(productOperation.getProductId()))
                .build();
    }

    public ProductOperation saleToRest(CreateSaleOperation productOperation) {
        Product product = productService.getById(productOperation.getProductId());

        if(productOperation.getAmount() == null && productOperation.getQuantity() == null){
            throw new BadRequestException("Have to specify the quantity or the price to sale");
        }

        BigDecimal quantity = productOperation.getQuantity() != null ?
                productOperation.getQuantity()
                : ProductService.calcQuantityFromPrice(product, productOperation.getAmount());
        return ProductOperation
                .builder()
                .id(productOperation.getId())
                .operationDatetime(productOperation.getOperationDatetime())
                .type(ProductOperationType.SALE)
                .quantity(quantity)
                .product(product)
                .build();
    }
}
