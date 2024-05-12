package com.ricka.princy.stationprojet1.endpoint.rest.controller;

import com.ricka.princy.stationprojet1.entity.ProductTemplate;
import com.ricka.princy.stationprojet1.service.ProductTemplateService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ProductTemplateController {
    private final ProductTemplateService productTemplateService;

    @GetMapping("/product_templates")
    public List<ProductTemplate> getAll(){
        return productTemplateService.getAllProductTemplates();
    }

    @GetMapping("/product_templates/{stationId}")
    public ProductTemplate getById(@PathVariable String stationId){
        return productTemplateService.getById(stationId);
    }

    @PutMapping("/product_templates")
    public List<ProductTemplate> saveOrUpdateAll(@RequestBody List<ProductTemplate> stations){
        return productTemplateService.saveOrUpdateAll(stations);
    }
}