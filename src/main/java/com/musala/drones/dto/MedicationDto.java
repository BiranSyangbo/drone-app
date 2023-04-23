package com.musala.drones.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicationDto {

    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String code;

    @NotBlank
    private double weight;

    private String image;
}
