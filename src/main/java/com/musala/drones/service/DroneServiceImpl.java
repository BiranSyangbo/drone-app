package com.musala.drones.service;

import com.musala.drones.dto.DroneDto;
import com.musala.drones.entity.Drone;
import com.musala.drones.exception.ResourceNotFoundException;
import com.musala.drones.mapper.DroneMapper;
import com.musala.drones.repository.DroneRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class DroneServiceImpl implements DroneService {

    private final DroneRepository droneRepository;
    private final DroneMapper mapper;

    @Override
    public DroneDto create(DroneDto droneDto) {
        Drone drone = droneRepository.save(mapper.dtoToEntity(droneDto));
        return mapper.entityToDto(drone);
    }

    @Override
    public DroneDto update(long id, DroneDto drone) {
        return droneRepository.findById(id)
                .map(__ -> mapper.entityToDto(droneRepository.save(mapper.dtoToEntity(drone))))
                .orElseThrow(() -> new ResourceNotFoundException("Drone", id));
    }

    @Override
    public DroneDto findById(long id) {
        return droneRepository.findById(id)
                .map(mapper::entityToDto)
                .orElseThrow(() -> new ResourceNotFoundException("Drone", id));
    }

    @Override
    public List<DroneDto> findAllDrones() {
        List<Drone> droneList = droneRepository.findAll();
        return droneList.stream().map(mapper::entityToDto).collect(Collectors.toList());
    }

    @Override
    public void delete(long id) {
        if (droneRepository.existsById(id))
            droneRepository.deleteById(id);
        throw new ResourceNotFoundException("Drone ", id);
    }
}
