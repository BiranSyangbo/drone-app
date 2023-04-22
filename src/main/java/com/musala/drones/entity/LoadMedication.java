package com.musala.drones.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "LOAD_MEDICATION")
@Table(name = "LOAD_MEDICATION")
@Getter
@Setter
public class LoadMedication {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_load_medication_id")
    @SequenceGenerator(name = "sequence_load_medication_id", allocationSize = 2)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "DRONE_ID")
    private Drone drone;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MEDICATION_ID")
    private Medication medications;
}
