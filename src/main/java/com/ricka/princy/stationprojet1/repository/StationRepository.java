package com.ricka.princy.stationprojet1.repository;

import com.ricka.princy.stationprojet1.fjpa.FJPARepository;
import com.ricka.princy.stationprojet1.entity.Station;
import org.springframework.stereotype.Repository;

import java.sql.Connection;

@Repository
public class StationRepository extends FJPARepository<Station> {
    public StationRepository(Connection connection) {
        super(connection);
    }
}
