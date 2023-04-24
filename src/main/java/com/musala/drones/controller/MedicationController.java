package com.musala.drones.controller;

import com.musala.drones.dto.MedicationDto;
import com.musala.drones.service.MedicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/medication")
@RequiredArgsConstructor
public class MedicationController {

    private final MedicationService medicationService;

    @PostMapping("/{droneId}/load")
    public ResponseEntity<String> loadMedication(@RequestBody List<@Valid MedicationDto> medications, @PathVariable("droneId") Long droneId) {
        medicationService.loadMedication(medications, droneId);
        return ResponseEntity.ok("Medication Load successfully");
    }

    @GetMapping("/{droneId}/list")
    public ResponseEntity<List<MedicationDto>> getMedicationDetailsByDrone(@PathVariable("droneId") long droneId) {
        return ResponseEntity.ok(medicationService.getMedicationForDrone(droneId));
    }
}
