package com.ricka.princy.stationprojet1.endpoint.rest.controller;

import com.ricka.princy.stationprojet1.endpoint.rest.mapper.ProductOperationMapper;
import com.ricka.princy.stationprojet1.endpoint.rest.model.CreateProductOperation;
import com.ricka.princy.stationprojet1.model.ProductOperation;
import com.ricka.princy.stationprojet1.service.ProductOperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductOperationController {
    private final ProductOperationService productOperationService;
    private final ProductOperationMapper productOperationMapper;

    @GetMapping("/operations")
    public List<ProductOperation> getAll(){
        return productOperationService.getAllProductOperations();
    }

    @GetMapping("/productOperations/{productOperationId}")
    public ProductOperation getById(@PathVariable String productOperationId){
        return productOperationService.getById(productOperationId);
    }

    @GetMapping("/stations/{productId}/productOperations")
    public List<ProductOperation> getByStationId(@PathVariable String productId){
        return productOperationService.getByProductId(productId);
    }

    @PutMapping("/operations")
    public List<ProductOperation> saveOrUpdateAll(@RequestBody List<CreateProductOperation> productOperations){
        return productOperationService.saveOrUpdateAll(productOperations.stream().map(productOperationMapper::toRest).toList());
    }
}
