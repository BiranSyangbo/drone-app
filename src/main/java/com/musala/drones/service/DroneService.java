package com.musala.drones.service;

import com.musala.drones.dto.DroneDto;
import com.musala.drones.entity.Drone;

import java.util.List;

public interface DroneService {
    DroneDto create(DroneDto drone);

    DroneDto update(long id, DroneDto drone);

    DroneDto findById(long id);

    List<DroneDto> findAllDrones();

    void delete(long id);

    Drone getAvailabilityDrone(Long droneId, double weight);
}
