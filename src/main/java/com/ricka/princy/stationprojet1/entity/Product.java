package com.ricka.princy.stationprojet1.entity;

import com.ricka.princy.stationprojet1.fjpa.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {
    @Id
    @Column
    private String id;

    @Column
    private BigDecimal evaporation;

    @Column(columnName = "unit_price")
    private BigDecimal unitPrice;

    @Column(columnName = "created_at")
    private Instant createdAt;

    @Column(columnName = "updated_at")
    private Instant updatedAt;

    @Relation
    @ValueGetter
    @Column(columnName = "station_id")
    private Station station;

    @Relation
    @ValueGetter
    @Column(columnName = "product_template_id")
    private ProductTemplate productTemplate;

    @Relation
    private BigDecimal stock;
}