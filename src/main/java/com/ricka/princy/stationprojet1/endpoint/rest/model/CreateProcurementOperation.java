package com.ricka.princy.stationprojet1.endpoint.rest.model;

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
public class CreateProcurementOperation implements Serializable {
    private String id;
    private Instant operationDatetime;
    private BigDecimal quantity;
    private String productId;
}