package com.musala.drones.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicationDto {

    private Long id;

    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z0-9_-]*$")
    private String name;

    @NotEmpty
    @Pattern(regexp = "^[A-Z0-9_]*$")
    private String code;

    private double weight;

    private String image;
}
