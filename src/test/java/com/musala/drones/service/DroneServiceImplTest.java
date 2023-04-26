package com.musala.drones.service;

import com.musala.drones.dto.DroneDto;
import com.musala.drones.entity.Drone;
import com.musala.drones.entity.Model;
import com.musala.drones.entity.State;
import com.musala.drones.exception.DroneNotAvailable;
import com.musala.drones.exception.ResourceAlreadyExist;
import com.musala.drones.exception.ResourceNotFoundException;
import com.musala.drones.mapper.DroneMapper;
import com.musala.drones.repository.DroneRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DroneServiceImplTest {

    @InjectMocks
    private DroneServiceImpl droneService;

    @Mock
    private DroneRepository droneRepository;

    @Mock
    private DroneMapper mapper;

    @Test
    void testCreate() {
        DroneDto dto = new DroneDto();
        dto.setModel(Model.Middleweight);
        when(mapper.dtoToEntity(dto)).thenReturn(new Drone());
        when(mapper.entityToDto(any(Drone.class)))
                .thenReturn(dto);
        when(droneRepository.save(any(Drone.class)))
                .thenReturn(new Drone());
        DroneDto result = droneService.create(dto);
        verify(droneRepository, times(1)).save(any(Drone.class));
        verify(droneRepository, times(1)).findBySerial(any());
        verify(mapper, times(1)).dtoToEntity(any());
        verify(mapper, times(1)).entityToDto(any());
        assertEquals(Model.Middleweight, result.getModel());
    }

    @Test
    void whenSerialIsAlreadyExist_ShouldThrowException() {
        DroneDto dto = new DroneDto();
        dto.setSerial("HEV-1");
        when(droneRepository.findBySerial(dto.getSerial())).thenReturn(Optional.of(new Drone()));
        assertThrows(ResourceAlreadyExist.class, () -> droneService.create(dto));
        verifyNoInteractions(mapper);
        verify(droneRepository, times(1)).findBySerial(dto.getSerial());
    }

    @Test
    void testUpdate() {
        DroneDto dto = new DroneDto();
        when(droneRepository.existsById(1L))
                .thenReturn(Boolean.TRUE);
        when(mapper.dtoToEntity(dto)).thenReturn(new Drone());
        when(mapper.entityToDto(any(Drone.class)))
                .thenReturn(dto);
        when(droneRepository.save(any(Drone.class)))
                .thenReturn(new Drone());
        DroneDto update = droneService.update(1L, dto);
        assertNotNull(update);
        verify(droneRepository, times(1)).existsById(1L);
        verify(droneRepository, times(1)).save(any(Drone.class));
        verify(mapper, times(1)).dtoToEntity(any());
        verify(mapper, times(1)).entityToDto(any());
    }


    @Test
    void whenUpdateDroneAndNotExistById_ShouldThrowException() {
        DroneDto dto = new DroneDto();
        dto.setSerial("HEV-1");
        when(droneRepository.existsById(1L)).thenReturn(Boolean.FALSE);
        assertThrows(ResourceNotFoundException.class, () -> droneService.update(1L, dto));
        verifyNoInteractions(mapper);
        verify(droneRepository, times(1)).existsById(1L);
    }


    @Test
    void testFindById() {
        when(droneRepository.findById(1L))
                .thenReturn(Optional.of(new Drone()));
        Drone drone = droneService.findById(1L);
        assertNotNull(drone);
        verifyNoInteractions(mapper);
    }

    @Test
    void shouldThrowException_IfResourceNotFound() {
        when(droneRepository.findById(2L))
                .thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> droneService.findById(2L));
    }

    @Test
    void testFindAll() {
        when(droneRepository.findAll())
                .thenReturn(List.of(new Drone(), new Drone()));
        when(mapper.entityToDto(any(Drone.class)))
                .thenReturn(new DroneDto());
        List<DroneDto> dtos = droneService.findAllDrones();
        assertEquals(2, dtos.size());
    }

    @Test
    void testgetAvailabilityDrone() {
        Drone drone = new Drone();
        drone.setId(1L);
        drone.setSerial("H-1");
        drone.setModel(Model.Middleweight);
        drone.setBatteryCapacity(90);
        drone.setWeight(200);
        drone.setState(State.IDLE);
        when(droneRepository.findById(drone.getId()))
                .thenReturn(Optional.of(drone));
        Drone dr = droneService.getAvailabilityDrone(drone.getId(), 100);
        assertNotNull(dr);
        assertEquals(dr.getModel(), Model.Middleweight);
    }

    @Test
    void testBatteryIsLessThan25() {
        Drone drone = new Drone();
        drone.setId(1L);
        drone.setSerial("H-1");
        drone.setModel(Model.Middleweight);
        drone.setBatteryCapacity(25);
        drone.setWeight(200);
        drone.setState(State.IDLE);
        when(droneRepository.findById(drone.getId()))
                .thenReturn(Optional.of(drone));
        assertThrows(DroneNotAvailable.class, () -> droneService.getAvailabilityDrone(drone.getId(), 100));
    }

    @Test
    void testWeightIsGreaterThanDroneCapacity() {
        Drone drone = new Drone();
        drone.setId(1L);
        drone.setSerial("H-1");
        drone.setModel(Model.Middleweight);
        drone.setBatteryCapacity(100);
        drone.setWeight(200);
        drone.setState(State.IDLE);
        when(droneRepository.findById(drone.getId()))
                .thenReturn(Optional.of(drone));
        assertThrows(DroneNotAvailable.class, () -> droneService.getAvailabilityDrone(drone.getId(), 300));
    }

    @Test
    void testDroneStateIsNotIdle() {
        Drone drone = new Drone();
        drone.setId(1L);
        drone.setSerial("H-1");
        drone.setModel(Model.Middleweight);
        drone.setBatteryCapacity(100);
        drone.setWeight(200);
        drone.setState(State.LOADED);
        when(droneRepository.findById(drone.getId()))
                .thenReturn(Optional.of(drone));
        assertThrows(DroneNotAvailable.class, () -> droneService.getAvailabilityDrone(drone.getId(), 100));
    }
}