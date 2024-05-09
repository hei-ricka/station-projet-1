package com.ricka.princy.stationprojet1.endpoint.rest.mapper;

import com.ricka.princy.stationprojet1.endpoint.rest.model.CreateProductOperation;
import com.ricka.princy.stationprojet1.model.ProductOperation;
import com.ricka.princy.stationprojet1.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProductOperationMapper {
    private final ProductService productService;

    public ProductOperation toRest(CreateProductOperation productOperation) {
        return ProductOperation
                .builder()
                .id(productOperation.getId())
                .operationDatetime(productOperation.getOperationDatetime())
                .type(productOperation.getType())
                .quantity(productOperation.getQuantity())
                .product(productService.getById(productOperation.getProductId()))
                .build();
    }
}
