package com.musala.drones.mapper;

import com.musala.drones.dto.DroneDto;
import com.musala.drones.entity.Drone;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface DroneMapper {

    Drone dtoToEntity(DroneDto dto);

    DroneDto entityToDto(Drone drone);
}
