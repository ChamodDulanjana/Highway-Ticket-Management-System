package com.chamoddulanjana.vehicalservice.controller;

import com.chamoddulanjana.vehicalservice.dto.VehicleDTO;
import com.chamoddulanjana.vehicalservice.entity.Vehicle;
import com.chamoddulanjana.vehicalservice.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/vehicle")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;
    private final Logger LOGGER = LoggerFactory.getLogger(VehicleController.class);

    @GetMapping("/health")
    public String healthCheck(){
        return "Vehicle Health Check";
    }

    @PostMapping
    public void registerVehicle(@RequestBody VehicleDTO vehicle){
        vehicleService.registerVehicle(vehicle);
    }

    @PutMapping
    public void updateVehicle(@RequestBody VehicleDTO vehicle, @RequestParam String id){
        vehicleService.updateVehicle(vehicle, id);
    }

    @GetMapping("/{id}")
    public VehicleDTO getVehicleById(@PathVariable String id){
        return vehicleService.getVehicleById(id);
    }

    @GetMapping("/getAll")
    public List<VehicleDTO> getAllVehicles(){
        return vehicleService.getAllVehicles();
    }
}
