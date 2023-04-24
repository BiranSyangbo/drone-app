package com.musala.drones.repository;

import com.musala.drones.entity.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DroneRepository extends JpaRepository<Drone, Long> {

    @Query("FROM DRONE d WHERE d.batteryCapacity < :batteryLevel")
    List<Drone> findAllByBatterPercentGreaterThan(@Param("batteryLevel") float batteryLevel);
}
