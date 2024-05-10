package com.ricka.princy.stationprojet1.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OperationStatement implements Serializable {
    private String productName;
    private BigDecimal procurementQuantity = BigDecimal.ZERO;
    private BigDecimal saleQuantity = BigDecimal.ZERO;
    private BigDecimal restQuantity = BigDecimal.ZERO;
}