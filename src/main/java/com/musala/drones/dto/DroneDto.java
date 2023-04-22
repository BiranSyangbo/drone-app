package com.musala.drones.dto;

import com.musala.drones.entity.Model;
import com.musala.drones.entity.State;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DroneDto {

    private Long id;

    @Size(max = 10)
    @NotEmpty
    private String serial;

    @NotNull
    private Model model;

    @Max(100)
    @NotNull
    private double weight;

    @NotNull
    private BigDecimal batteryCapacity;

    private State state;

}
