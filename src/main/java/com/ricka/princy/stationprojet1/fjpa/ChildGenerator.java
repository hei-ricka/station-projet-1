package com.ricka.princy.stationprojet1.fjpa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class ChildGenerator {
    private QueryGenerator<?> queryGenerator;
    private ReflectEntity<?> reflectEntity;
}