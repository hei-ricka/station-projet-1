package com.ricka.princy.stationprojet1.fjpa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class FilterValues {
    private String query;
    private List<Object> values;
}
