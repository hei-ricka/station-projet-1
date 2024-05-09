package com.ricka.princy.stationprojet1.repository;

import com.ricka.princy.stationprojet1.fjpa.FJPARepository;
import com.ricka.princy.stationprojet1.model.Dummy;
import org.springframework.stereotype.Repository;

import java.sql.Connection;

@Repository
public class DummyRepository extends FJPARepository<Dummy> {
    public DummyRepository(Connection connection) {
        super(connection);
    }
}
