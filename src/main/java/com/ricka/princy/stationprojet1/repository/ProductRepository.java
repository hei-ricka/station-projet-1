package com.ricka.princy.stationprojet1.repository;

import com.ricka.princy.stationprojet1.entity.ProductOperation;
import com.ricka.princy.stationprojet1.exception.InternalServerErrorException;
import com.ricka.princy.stationprojet1.fjpa.FJPARepository;
import com.ricka.princy.stationprojet1.entity.Product;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProductRepository extends FJPARepository<Product> {
    private final ProductOperationRepository productOperationRepository;

    public ProductRepository(Connection connection, ProductOperationRepository productOperationRepository) {
        super(connection);
        this.productOperationRepository = productOperationRepository;
    }

    public BigDecimal getCurrentStockByProductId(Product product) throws SQLException {
        return this.getStockInDateByProductId(product, Instant.now());
    }

    public BigDecimal getStockInDateByProductId(Product product, Instant datetime) throws SQLException {
        ProductOperation lastProductOperation = this.productOperationRepository.getLatestOperationInDateByProductId(product.getId(), datetime);
        String query = """
            SELECT sum(@quantity) as stock_value
            FROM @entity WHERE @product = ? AND @operationDatetime <= ?
        """;

        List<BigDecimal> stocks = this.productOperationRepository.getStatementWrapper().select(query, List.of(product.getId(), datetime), resultSet -> {
            try {
                return resultSet.getBigDecimal("stock_value");
            } catch (SQLException e) {
                throw new InternalServerErrorException(e);
            }
        });
        if(stocks.isEmpty() || lastProductOperation == null)
            return BigDecimal.ZERO;
        long days = Duration.between(lastProductOperation.getOperationDatetime(), datetime).toDays();
        BigDecimal toRemove = product.getEvaporation().multiply(new BigDecimal(days));
        BigDecimal currentsStock = stocks.getFirst().min(toRemove);

        return currentsStock.compareTo(BigDecimal.valueOf(0)) < 0 ? BigDecimal.ZERO : currentsStock;
    }

    public Product mapStockToCurrentStock(Product product){
        try {
            product.setStock(this.getCurrentStockByProductId(product));
        } catch (SQLException e) {
            throw new InternalServerErrorException(e);
        }
        return product;
    }

    public List<Product> findByStationId(String stationId) throws SQLException {
        return this.findByField("@station", stationId);
    }

    @Override
    public List<Product> findAll() throws SQLException {
        return super.findAll().stream().map(this::mapStockToCurrentStock).collect(Collectors.toList());
    }

    @Override
    public List<Product> findByCondition(String condition, List<Object> values, String suffix, List<Class<?>> excludes) throws SQLException {
        return super.findByCondition(condition, values, suffix, excludes).stream().map(this::mapStockToCurrentStock).collect(Collectors.toList());
    }
}