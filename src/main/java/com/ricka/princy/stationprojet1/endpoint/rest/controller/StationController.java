package com.ricka.princy.stationprojet1.endpoint.rest.controller;

import com.ricka.princy.stationprojet1.model.Station;
import com.ricka.princy.stationprojet1.service.StationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class StationController {
    private final StationService stationService;

    @GetMapping("/stations")
    public List<Station> getAll(){
        return stationService.getAllStations();
    }

    @GetMapping("/stations/{stationId}")
    public Station getById(@PathVariable String stationId){
        return stationService.getById(stationId);
    }

    @PutMapping("stations")
    public List<Station> saveOrUpdateAll(@RequestBody List<Station> stations){
        return stationService.saveOrUpdateAll(stations);
    }
}