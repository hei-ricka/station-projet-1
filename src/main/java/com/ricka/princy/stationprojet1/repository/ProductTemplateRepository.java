package com.ricka.princy.stationprojet1.repository;

import com.ricka.princy.stationprojet1.entity.ProductTemplate;
import com.ricka.princy.stationprojet1.fjpa.FJPARepository;
import org.springframework.stereotype.Repository;

import java.sql.Connection;

@Repository
public class ProductTemplateRepository extends FJPARepository<ProductTemplate> {
    public ProductTemplateRepository(Connection connection) {
        super(connection);
    }
}