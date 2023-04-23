package com.musala.drones.controller;

import com.musala.drones.dto.MedicationDto;
import com.musala.drones.service.MedicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medication")
@RequiredArgsConstructor
public class MedicationController {

    private final MedicationService medicationService;

    @PostMapping("/{droneId}/load")
    public ResponseEntity<String> loadMedication(@RequestBody List<MedicationDto> medications, @PathVariable("droneId") Long droneId) {
        medicationService.loadMedication(medications, droneId);
        return ResponseEntity.ok("Medication Load successfully");
    }

    @GetMapping("/{droneId}/list")
    public ResponseEntity<List<MedicationDto>> getMedicationDetailsByDrone(@PathVariable("droneId") long droneId) {
        return ResponseEntity.ok(medicationService.getMedicationForDrone(droneId));
    }
}
