package com.ricka.princy.stationprojet1.fjpa;

import lombok.*;

import java.lang.reflect.Method;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@EqualsAndHashCode
public class ReflectAttribute {
    private String sqlColumnName;
    private String originalColumnName;
    private String originalTableName;
    private String sqlTableName;
    private String fieldName;
    private String valueGetter;
    private boolean required;
    private boolean isId;
    private Method setter;
    private Method getter;
    private boolean isRelation;
    private String refColumnName;
    private Class<?> clazz;
}
