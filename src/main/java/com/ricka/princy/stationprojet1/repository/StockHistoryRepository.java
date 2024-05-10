package com.ricka.princy.stationprojet1.repository;

import com.ricka.princy.stationprojet1.fjpa.FJPARepository;
import com.ricka.princy.stationprojet1.model.StockHistory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.Instant;
import java.util.List;

@Repository
public class StockHistoryRepository extends FJPARepository<StockHistory> {
    public StockHistoryRepository(Connection connection) {
        super(connection);
    }

    public StockHistory getStockInDate(String productId, Instant datetime) throws SQLException {
        List<StockHistory> stockHistories = this.findByCondition(" @product = ? and @createdAt <= ? order by @createdAt desc limit 1", List.of(productId, datetime));
        if(stockHistories.isEmpty()){
            return null;
        }
        return stockHistories.getFirst();
    }

    public StockHistory getCurrentStockByProductId(String productId) throws SQLException {
        return this.getStockInDate(productId, Instant.now());
    }

    @Override
    public List<StockHistory> findAll() throws SQLException {
        return super.findAll(" order by @createdAt", List.of());
    }
}