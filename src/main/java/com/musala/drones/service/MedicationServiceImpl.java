package com.musala.drones.service;

import com.musala.drones.dto.MedicationDto;
import com.musala.drones.entity.Drone;
import com.musala.drones.entity.Medication;
import com.musala.drones.mapper.MedicationMapper;
import com.musala.drones.repository.MedicationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MedicationServiceImpl implements MedicationService {

    private final DroneService droneService;
    private final MedicationMapper medicationMapper;
    private final MedicationRepository medicationRepository;

    @Override
    public void loadMedication(List<MedicationDto> medicationDtos, Long droneId) {
        double totalWeight = medicationDtos.stream()
                .map(MedicationDto::getWeight)
                .reduce(0d, Double::sum);
        Drone drone = droneService.getAvailabilityDrone(droneId, totalWeight);
        List<Medication> medications = medicationMapper.dtoToEntityList(medicationDtos)
                .stream()
                .peek(e -> e.setDrone(drone))
                .collect(Collectors.toList());
        medicationRepository.saveAll(medications);
    }
}
