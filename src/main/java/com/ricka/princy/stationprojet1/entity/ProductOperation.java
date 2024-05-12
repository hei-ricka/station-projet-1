package com.ricka.princy.stationprojet1.entity;

import com.ricka.princy.stationprojet1.fjpa.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity(tableName = "product_operation")
public class ProductOperation implements Serializable {
    @Id
    @Column
    private String id;

    @Column(columnName = "operation_datetime")
    private Instant operationDatetime;

    @Column
    private ProductOperationType type;

    @Column
    private BigDecimal quantity;

    @Relation
    @ValueGetter
    @Column(columnName = "product_id")
    private Product product;
}