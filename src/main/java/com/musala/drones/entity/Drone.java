package com.musala.drones.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity(name = "DRONE")
@Table(name = "DRONE")
@Getter
@Setter
public class Drone {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_drones_id")
    @SequenceGenerator(name = "sequence_drones_id", allocationSize = 2)
    @Column(name = "ID")
    private Long id;

    @Size(max = 100)
    @NotEmpty
    @Column(length = 100, name = "SERIAL_NUMBER", nullable = false)
    private String serial;

    @Column(name = "MODEL")
    @Enumerated(EnumType.ORDINAL)
    @NotNull
    private Model model;

    @Max(500)
    @Column(name = "WEIGHT")
    @NotNull
    private double weight;

    @Column(precision = 4, scale = 2, name = "BATTERY_CAPACITY")
    @NotNull
    @Max(100)
    private BigDecimal batteryCapacity;

    @Column(name = "STATE")
    @Enumerated(EnumType.ORDINAL)
    private State state;


}
