package com.ricka.princy.stationprojet1.service;

import com.ricka.princy.stationprojet1.exception.InternalServerErrorException;
import com.ricka.princy.stationprojet1.model.Product;
import com.ricka.princy.stationprojet1.model.StockHistory;
import com.ricka.princy.stationprojet1.repository.StockHistoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;

@Service
@AllArgsConstructor
public class StockHistoryService {
    private final StockHistoryRepository stockHistoryRepository;

    public StockHistory getProductCurrentStockInDate(String productId, Instant datetime){
        try {
            StockHistory stockHistory = stockHistoryRepository.getStockInDate(productId, datetime);
            if(stockHistory == null){
                return new StockHistory(
                        "default",
                        Instant.now(),
                        BigDecimal.ZERO,
                        Product.builder().id(productId).build()
                );
            }
            Duration duration = Duration.between(stockHistory.getCreatedAt(), datetime);
            long days = duration.toDays();

            BigDecimal toRemove = stockHistory.getProduct().getEvaporation().multiply(new BigDecimal(days));
            stockHistory.setQuantity(stockHistory.getQuantity().min(toRemove));
            return stockHistory;
        } catch (SQLException e) {
            throw new InternalServerErrorException(e);
        }
    }
    public StockHistory getProductCurrentStock(String productId){
        return this.getProductCurrentStockInDate(productId, Instant.now());
    }

    public StockHistory saveOrUpdate(StockHistory stockHistory){
        try {
            return stockHistoryRepository.saveOrUpdate(stockHistory);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
