package com.ricka.princy.stationprojet1.service;

import com.ricka.princy.stationprojet1.exception.NotFoundException;
import com.ricka.princy.stationprojet1.model.ProductOperation;
import com.ricka.princy.stationprojet1.repository.ProductOperationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductOperationService {
    private final ProductOperationRepository productOperationRepository;

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
}
