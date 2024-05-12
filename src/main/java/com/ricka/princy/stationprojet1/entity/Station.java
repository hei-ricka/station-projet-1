package com.ricka.princy.stationprojet1.entity;

import com.ricka.princy.stationprojet1.fjpa.annotation.Column;
import com.ricka.princy.stationprojet1.fjpa.annotation.Entity;
import com.ricka.princy.stationprojet1.fjpa.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Station implements Serializable {
    @Id
    @Column
    private String id;

    @Column
    private String location;

    @Column(columnName = "created_at")
    private Instant createdAt;

    @Column(columnName = "updated_at")
    private Instant updatedAt;
}
