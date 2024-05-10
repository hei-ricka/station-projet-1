package com.ricka.princy.stationprojet1.repository;

import com.ricka.princy.stationprojet1.fjpa.FJPARepository;
import com.ricka.princy.stationprojet1.model.ProductOperation;
import com.ricka.princy.stationprojet1.model.ProductOperationType;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductOperationRepository extends FJPARepository<ProductOperation> {
    public ProductOperationRepository(Connection connection) {
        super(connection);
    }

    public List<ProductOperation> findByProductId(String productId) throws SQLException {
        return this.findByField("@product", productId);
    }

    public List<ProductOperation> findByStationId(String stationId, Instant from, Instant to, ProductOperationType type) throws SQLException {
        StringBuilder condition = new StringBuilder("\"station\".\"id\" =  ? and (@operationDatetime between ? and ? ) ");
        List<Object> values = new ArrayList<>(List.of(stationId, from, to));

        if(type != null){
            condition.append(" and @type = ?");
            values.add(type);
        }
        condition.append(" oder by @operationDatetime DESC");

        return this.findByCondition(condition.toString(), values);
    }
}
