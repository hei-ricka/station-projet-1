package com.ricka.princy.stationprojet1.service;

import com.ricka.princy.stationprojet1.exception.NotFoundException;
import com.ricka.princy.stationprojet1.model.Station;
import com.ricka.princy.stationprojet1.repository.StationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@AllArgsConstructor
public class StationService {
    private final StationRepository stationRepository;

    public List<Station> getAllStations(){
        try {
            return stationRepository.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Station> saveOrUpdateAll(List<Station> stations){
        try {
            return stationRepository.saveOrUpdateAll(stations);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Station getById(String stationId){
        try {
            Station station = stationRepository.findById(stationId);
            if(station == null){
                throw new NotFoundException(String.format("Station with {id: %s} is not found", stationId));
            }
            return station;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
