package com.ricka.princy.stationprojet1.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OperationStatementValues implements Serializable {
    private Instant datetime;
    private List<OperationStatement> operationStatements;
}
