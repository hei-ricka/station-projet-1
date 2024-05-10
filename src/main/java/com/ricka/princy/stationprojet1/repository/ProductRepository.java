package com.ricka.princy.stationprojet1.repository;

import com.ricka.princy.stationprojet1.fjpa.FJPARepository;
import com.ricka.princy.stationprojet1.model.Product;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Repository final
public class ProductRepository extends FJPARepository<Product> {
    public ProductRepository(Connection connection, ProductRepositoryMapper productRepositoryMapper) {
        super(connection);
        this.setResultSetMapper(productRepositoryMapper);
    }

    public List<Product> findByStationId(String stationId) throws SQLException {
        return this.findByField("@station", stationId);
    }
}
