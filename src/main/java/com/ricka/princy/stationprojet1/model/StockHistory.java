package com.ricka.princy.stationprojet1.model;

import com.ricka.princy.stationprojet1.fjpa.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(tableName = "stock_history")
public class StockHistory implements Serializable{
    @Id
    @Column
    private String id;

    @Column(columnName = "created_at")
    private Instant createdAt;

    @Column
    private BigDecimal quantity;

    @Relation
    @ValueGetter
    @Column(columnName = "product_id")
    private Product product;
}