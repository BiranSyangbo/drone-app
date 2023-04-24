package com.musala.drones.repository;

import com.musala.drones.entity.DroneStatusAuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DroneStatusAuditLogRepository extends JpaRepository<DroneStatusAuditLog, Long> {
}
