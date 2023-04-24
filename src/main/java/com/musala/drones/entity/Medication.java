package com.musala.drones.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "MEDICATION")
@Table(name = "MEDICATION")
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_medication_id")
    @SequenceGenerator(name = "sequence_medication_id", allocationSize = 2)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    @NotEmpty
    private String name;

    @Column(name = "CODE")
    @NotEmpty
    private String code;

    @Column(name = "WEIGHT")
    private double weight;

    @Column(name = "IMAGE", columnDefinition = "TEXT")
    private String image;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "DRONE_ID")
    private Drone drone;
}
