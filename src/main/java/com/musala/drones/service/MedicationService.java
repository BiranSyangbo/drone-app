package com.musala.drones.service;

import com.musala.drones.dto.MedicationDto;

import java.util.List;

public interface MedicationService {
    void loadMedication(List<MedicationDto> medications, Long droneId);

    List<MedicationDto> getMedicationForDrone(Long droneId);
}
