package com.ricka.princy.stationprojet1.service;

import com.ricka.princy.stationprojet1.entity.ProductTemplate;
import com.ricka.princy.stationprojet1.exception.NotFoundException;
import com.ricka.princy.stationprojet1.repository.ProductTemplateRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductTemplateService {
    private final ProductTemplateRepository productTemplateRepository;

    public List<ProductTemplate> getAllProductTemplates(){
        try {
            return productTemplateRepository.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ProductTemplate> saveOrUpdateAll(List<ProductTemplate> productTemplates){
        try {
            return productTemplateRepository.saveOrUpdateAll(productTemplates);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ProductTemplate getById(String productTemplateId){
        try {
            ProductTemplate productTemplate = productTemplateRepository.findById(productTemplateId);
            if(productTemplate == null){
                throw new NotFoundException(String.format("ProductTemplate with {id: %s} is not found", productTemplateId));
            }
            return productTemplate;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}