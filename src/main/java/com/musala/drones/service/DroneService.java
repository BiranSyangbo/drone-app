package com.musala.drones.service;

import com.musala.drones.dto.DroneDto;

import java.util.List;

public interface DroneService {
    DroneDto create(DroneDto drone);

    DroneDto update(long id, DroneDto drone);

    DroneDto findById(long id);

    List<DroneDto> findAllDrones();

    void delete(long id);
}
