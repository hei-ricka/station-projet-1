package com.ricka.princy.stationprojet1.repository;

import com.ricka.princy.stationprojet1.fjpa.FJPARepository;
import com.ricka.princy.stationprojet1.model.ProductOperation;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProductOperationRepository extends FJPARepository<ProductOperation> {
    public ProductOperationRepository(Connection connection) {
        super(connection);
    }

    public List<ProductOperation> findByProductId(String productId) throws SQLException {
        return this.findByField("@product", productId);
    }
}
