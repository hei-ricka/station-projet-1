package com.ricka.princy.stationprojet1.endpoint.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreateSaleOperation implements Serializable{
    private String id;
    private Instant operationDatetime;
    private BigDecimal quantity;
    private BigDecimal amount;
    private String productId;
}
