package com.musala.drones.dto;

import com.musala.drones.entity.Model;
import com.musala.drones.entity.State;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DroneDto {

    private Long id;

    @Size(max = 100)
    @NotEmpty
    private String serial;

    @NotNull
    private Model model;

    @Max(500)
    @NotNull
    private double weight;

    @NotNull
    private float batteryCapacity;

    private State state;

}
