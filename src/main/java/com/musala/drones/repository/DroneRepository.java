package com.musala.drones.repository;

import com.musala.drones.entity.Drone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DroneRepository extends JpaRepository<Drone, Long> {
}
