package com.ricka.princy.stationprojet1.endpoint.rest.controller;

import com.ricka.princy.stationprojet1.model.Dummy;
import com.ricka.princy.stationprojet1.repository.DummyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class HealthController {
    private final DummyRepository dummyRepository;

    @GetMapping("dummy-table")
    public List<Dummy> getDummies() throws SQLException {
        return dummyRepository.findAll();
    }

    @GetMapping("/ping")
    public String ping(){
        return "pong";
    }
}
