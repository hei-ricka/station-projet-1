package com.ricka.princy.stationprojet1.repository;

import com.ricka.princy.stationprojet1.fjpa.ReflectEntity;
import com.ricka.princy.stationprojet1.fjpa.ResultSetMapper;
import com.ricka.princy.stationprojet1.model.Product;
import com.ricka.princy.stationprojet1.service.StockHistoryService;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.List;

@Component
public class ProductRepositoryMapper extends ResultSetMapper<Product> {
    private final StockHistoryService stockHistoryService;

    public ProductRepositoryMapper(StockHistoryService stockHistoryService) {
        super(new ReflectEntity<>(Product.class));
        this.stockHistoryService = stockHistoryService;
    }

    @Override
    public Product mapResultSetToInstance(ResultSet resultSet, List<Class<?>> excludes, boolean isUpdate) {
        Product product = super.mapResultSetToInstance(resultSet, excludes, isUpdate);
        product.setStock(stockHistoryService.getProductCurrentStock(product.getId()).getQuantity());
        return product;
    }
}
