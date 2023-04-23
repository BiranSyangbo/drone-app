package com.musala.drones.controller;

import com.musala.drones.dto.DroneDto;
import com.musala.drones.entity.State;
import com.musala.drones.service.DroneService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drone")
@RequiredArgsConstructor
public class DronesController {

    private final DroneService droneService;

    @PostMapping("/register")
    public ResponseEntity<DroneDto> registerDrone(@Valid @RequestBody DroneDto drone) {
        return ResponseEntity.ok(droneService.create(drone));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DroneDto> update(@PathVariable("id") long id, @RequestBody DroneDto drone) {
        return ResponseEntity.ok(droneService.update(id, drone));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DroneDto> getDroneById(@PathVariable("id") long id) {
        return ResponseEntity.ok(droneService.getById(id));
    }

    @GetMapping("/list")
    public ResponseEntity<List<DroneDto>> getListOfDrone() {
        return ResponseEntity.ok(droneService.findAllDrones());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDrone(@PathVariable("id") long id) {
        droneService.delete(id);
        return ResponseEntity.ok("Delete Successfully");
    }
}
