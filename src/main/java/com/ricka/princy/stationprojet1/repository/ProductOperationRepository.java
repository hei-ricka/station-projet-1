package com.ricka.princy.stationprojet1.repository;

import com.ricka.princy.stationprojet1.fjpa.FJPARepository;
import com.ricka.princy.stationprojet1.entity.ProductOperation;
import com.ricka.princy.stationprojet1.entity.ProductOperationType;
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

    public ProductOperation getLatestOperationInDateByProductId(String productId, Instant datetime) throws SQLException {
        List<ProductOperation> operations = this.findByCondition("@product = ? and @operationDatetime <= ? order by @operationDatetime desc limit 1", List.of(productId, datetime));
        return operations.isEmpty() ? null : operations.getFirst();
    }

    public List<ProductOperation> findByStationId(String stationId, Instant from, Instant to, ProductOperationType type) throws SQLException {
        StringBuilder condition = new StringBuilder("\"station\".\"id\" =  ? and (@operationDatetime between ? and ? ) ");
        List<Object> values = new ArrayList<>(List.of(stationId, from, to));

        if(type != null){
            condition.append(" and @type = ?");
            values.add(type);
        }
        condition.append(" order by @operationDatetime DESC");

        return this.findByCondition(condition.toString(), values);
    }
}