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
@Builder
@Data
public class CrupdateProduct implements Serializable {
    private String id;
    private BigDecimal unitPrice;
    private BigDecimal evaporation;
    private Instant createdAt;
    private Instant updatedAt;
    private String stationId;
    private String productTemplateId;
}