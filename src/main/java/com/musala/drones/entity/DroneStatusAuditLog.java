package com.musala.drones.entity;

import jakarta.persistence.*;
import lombok.Builder;

// To auditing only store the drone id, battery percent and state;
@Entity
@Table(name = "AUDIT_LOG")
@Builder
public class DroneStatusAuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_drones_audit_log_id")
    @SequenceGenerator(name = "sequence_drones_audit_log_id", allocationSize = 2)
    private Long id;

    // TODO remove the relation with db because, in this table data will be persisted based on periodic time and might impact on db
    @Column(name = "DRONE_ID", nullable = false)
    private Long droneId;

    @Column(name = "BATTERY_PERCENT", nullable = false)
    private float batteryPercent;

    @Column(name = "STATE", nullable = false)
    @Enumerated(EnumType.STRING)
    private State state;
}
