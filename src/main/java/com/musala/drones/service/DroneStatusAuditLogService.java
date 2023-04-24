package com.musala.drones.service;

import com.musala.drones.entity.Drone;
import com.musala.drones.entity.DroneStatusAuditLog;
import com.musala.drones.repository.DroneRepository;
import com.musala.drones.repository.DroneStatusAuditLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@EnableAsync
@RequiredArgsConstructor
@Slf4j
public class DroneStatusAuditLogService {

    private final DroneRepository droneRepository;
    private final DroneStatusAuditLogRepository repository;

    // TODO used spring schedule to trigger the function on every certain time to check drone status
    @Async
    @Scheduled(fixedDelay = 15, timeUnit = TimeUnit.MINUTES)
    public void checkDroneStatus() {
        List<Drone> drones = droneRepository.findAllByBatterPercentGreaterThan(25f);
        log.warn("Total drone with batter level is less than 25 is {}", drones.size());
        if (drones.isEmpty())
            return;
        List<DroneStatusAuditLog> auditLogs = drones.stream().map(drone -> DroneStatusAuditLog.builder()
                .droneId(drone.getId())
                .batteryPercent(drone.getBatteryCapacity())
                .state(drone.getState())
                .build())
                .collect(Collectors.toList());
        repository.saveAll(auditLogs);
        repository.flush();
    }
}
