package com.ricka.princy.stationprojet1.endpoint.rest.controller;

import com.ricka.princy.stationprojet1.endpoint.rest.mapper.ProductOperationMapper;
import com.ricka.princy.stationprojet1.endpoint.rest.model.CreateProcurementOperation;
import com.ricka.princy.stationprojet1.endpoint.rest.model.CreateSaleOperation;
import com.ricka.princy.stationprojet1.model.OperationStatementValues;
import com.ricka.princy.stationprojet1.model.ProductOperation;
import com.ricka.princy.stationprojet1.model.ProductOperationType;
import com.ricka.princy.stationprojet1.service.ProductOperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
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

    @GetMapping("/operations/{operationId}")
    public ProductOperation getById(@PathVariable String operationId){
        return productOperationService.getById(operationId);
    }

    @GetMapping("/products/{productId}/operations")
    public List<ProductOperation> getByProductId(@PathVariable String productId){
        return productOperationService.getByProductId(productId);
    }

    @GetMapping("/stations/{stationId}/operations")
    public List<ProductOperation> getByStationId(
            @PathVariable String stationId,
            @RequestParam Instant from,
            @RequestParam Instant to,
            @RequestParam(required = false) ProductOperationType type
    ){
        return productOperationService.getByStationId(stationId, from, to, type);
    }

    @PutMapping("/operations/procurements")
    public ProductOperation doProcurement(@RequestBody CreateProcurementOperation productOperation){
        return productOperationService.doOperations(productOperationMapper.procurementToRest(productOperation));
    }

    @PutMapping("/operations/sales")
    public ProductOperation doSale(@RequestBody CreateSaleOperation productOperation){
        return productOperationService.doOperations(productOperationMapper.saleToRest(productOperation));
    }

    @GetMapping("/stations/{stationId}/operations/statements")
    public List<OperationStatementValues> getStatements(
            @PathVariable String stationId,
            @RequestParam Instant from,
            @RequestParam Instant to
    ){
        return productOperationService.getOperationStatementByIdPerDay(stationId, from, to);
    }
}