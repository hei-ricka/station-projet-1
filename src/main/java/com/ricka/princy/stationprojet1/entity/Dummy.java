package com.ricka.princy.stationprojet1.entity;

import com.ricka.princy.stationprojet1.fjpa.annotation.Column;
import com.ricka.princy.stationprojet1.fjpa.annotation.Entity;
import com.ricka.princy.stationprojet1.fjpa.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Dummy implements Serializable {
    @Id
    @Column
    private String id;

    @Column
    private String name;
}
