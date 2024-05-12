package com.ricka.princy.stationprojet1.endpoint.rest.controller;

import com.ricka.princy.stationprojet1.endpoint.rest.mapper.ProductMapper;
import com.ricka.princy.stationprojet1.endpoint.rest.model.CrupdateProduct;
import com.ricka.princy.stationprojet1.entity.Product;
import com.ricka.princy.stationprojet1.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping("/products")
    public List<Product> getAll(){
        return productService.getAllProducts();
    }

    @GetMapping("/products/{productId}")
    public Product getById(@PathVariable String productId){
        return productService.getById(productId);
    }

    @GetMapping("/stations/{stationId}/products")
    public List<Product> getByStationId(@PathVariable String stationId){
        return productService.getByStationId(stationId);
    }

    @PutMapping("products")
    public List<Product> saveOrUpdateAll(@RequestBody List<CrupdateProduct> products){
        return productService.saveOrUpdateAll(products.stream().map(productMapper::toRest).toList());
    }
}