package com.musala.drones.service;

import com.musala.drones.dto.DroneDto;
import com.musala.drones.entity.Drone;
import com.musala.drones.exception.DroneNotAvailable;
import com.musala.drones.exception.ResourceAlreadyExist;
import com.musala.drones.exception.ResourceNotFoundException;
import com.musala.drones.mapper.DroneMapper;
import com.musala.drones.repository.DroneRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
        checkDroneAlreadyExist(droneDto.getSerial());
        Drone drone = droneRepository.save(mapper.dtoToEntity(droneDto));
        return mapper.entityToDto(drone);
    }

    private void checkDroneAlreadyExist(String serial) {
        if (droneRepository.findBySerial(serial).isPresent()) {
            throw new ResourceAlreadyExist("Drone ", serial);
        }
    }

    @Override
    public DroneDto update(long id, DroneDto droneDto) {
        if (!droneRepository.existsById(id))
            throw new ResourceNotFoundException("Drone", id);
        Drone drone = mapper.dtoToEntity(droneDto);
        drone.setId(id);
        return mapper.entityToDto(droneRepository.save(drone));

    }

    @Override
    public DroneDto getById(long id) {
        return mapper.entityToDto(findById(id));
    }


    @Override
    public Drone findById(long id) {
        return droneRepository.findById(id)
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

    @Override
    public Drone getAvailabilityDrone(Long droneId, double weight) {
        return droneRepository.findById(droneId)
                .map(drone -> {
                    if (drone.getState().isIdle())
                        throw new DroneNotAvailable("Drone with id:- " + droneId + "  is " + drone.getState());
                    if (checkWight(weight, drone.getWeight()))
                        throw new DroneNotAvailable("Drone with id:- " + droneId + "  weight is greater than " + weight + ">" + drone.getWeight());
                    if (!checkDroneBatteryAvailable(drone.getBatteryCapacity()))
                        throw new DroneNotAvailable("Drone with id:- " + droneId + "  battery percent is lower than " + drone.getBatteryCapacity() + " < 25");
                    return drone;
                })
                .orElseThrow(() -> new ResourceNotFoundException("Drone", droneId));
    }

    private boolean checkDroneBatteryAvailable(float batteryPercent) {
        return batteryPercent > 25;
    }

    private boolean checkWight(double weight, double droneWeight) {
        return weight > droneWeight;
    }
}
