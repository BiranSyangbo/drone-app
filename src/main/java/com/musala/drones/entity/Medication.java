package com.musala.drones.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

@Entity(name = "MEDICATION")
@Table(name = "MEDICATION")
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_medication_id")
    @SequenceGenerator(name = "sequence_medication_id", allocationSize = 2)
    @Column(name = "ID")
    private Long id;

    //    @Pattern("")
    //TODO regex baki xa
    @Column(name = "NAME")
    @NotEmpty
    private String name;

    @Column(name = "CODE")
    @NotEmpty
    private String code;

    @Column(name = "WEIGHT")
    @NotBlank
    private double weight;

    @Column(name = "IMAGE_PATH")
    private String imagePath;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "DRONE_ID")
    private Drone drone;
}
