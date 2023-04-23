package com.musala.drones.mapper;

import com.musala.drones.dto.MedicationDto;
import com.musala.drones.entity.Medication;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface MedicationMapper {

    List<Medication> dtoToEntityList(List<MedicationDto> medications);

    List<MedicationDto> entityToDtoList(List<Medication> medications);

    Medication dtoToEntity(MedicationDto medicationDto);

    MedicationDto entityToDto(Medication medication);


}
