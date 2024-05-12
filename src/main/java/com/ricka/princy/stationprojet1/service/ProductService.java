package com.ricka.princy.stationprojet1.service;

import com.ricka.princy.stationprojet1.exception.NotFoundException;
import com.ricka.princy.stationprojet1.entity.Product;
import com.ricka.princy.stationprojet1.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getAllProducts(){
        try {
            return productRepository.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Product saveOrUpdate(Product product){
        try {
            productRepository.saveOrUpdate(product);
            return productRepository.findById(product.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Product> saveOrUpdateAll(List<Product> products){
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
           result.add(this.saveOrUpdate(product));
        }
        return result;
    }

    public List<Product> getByStationId(String stationId){
        try {
            return productRepository.findByStationId(stationId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Product getById(String productId){
        try {
            Product product = productRepository.findById(productId);
            if(product == null){
                throw new NotFoundException(String.format("Product with {id: %s} is not found", productId));
            }
            return product;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static BigDecimal calcQuantityFromPrice(Product product, BigDecimal price){
        return price.divide(product.getUnitPrice(), RoundingMode.HALF_DOWN);
    }
}