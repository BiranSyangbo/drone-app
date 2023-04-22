package com.musala.drones.mapper;

import com.musala.drones.dto.DroneDto;
import com.musala.drones.entity.Drone;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DroneMapper {

    Drone dtoToEntity(DroneDto dto);

    DroneDto entityToDto(Drone drone);
}
