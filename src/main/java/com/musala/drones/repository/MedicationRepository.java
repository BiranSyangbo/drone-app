package com.musala.drones.repository;

import com.musala.drones.entity.Drone;
import com.musala.drones.entity.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicationRepository extends JpaRepository<Medication, Long> {

    List<Medication> findAllByDrone(Drone drone);
}
