package com.chamoddulanjana.vehicalservice.controller;

import com.chamoddulanjana.vehicalservice.dto.VehicleDTO;
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
        LOGGER.info("Registering vehicle request: {}", vehicle);
        vehicleService.registerVehicle(vehicle);
    }

    @PutMapping
    public void updateVehicle(@RequestBody VehicleDTO vehicle, @RequestParam String id){
        LOGGER.info("Updating vehicle request: {}", vehicle.getLicencePlateNumber());
        vehicleService.updateVehicle(vehicle, id);
    }

    @GetMapping("/{licencePlateNumber}")
    public VehicleDTO getVehicleByLicencePlateNumber(@PathVariable String licencePlateNumber){
        LOGGER.info("Retrieving vehicle request by Email: {}", licencePlateNumber);
        return vehicleService.getVehicleByLicencePlateNumber(licencePlateNumber);
    }

    @GetMapping("/id/{id}")
    public VehicleDTO getVehicleById(@PathVariable String id){
        LOGGER.info("Retrieving vehicle request by Id: {}", id);
        return vehicleService.getVehicleById(id);
    }

    @GetMapping("/getAll")
    public List<VehicleDTO> getAllVehicles(){
        LOGGER.info("Retrieving all vehicles request");
        return vehicleService.getAllVehicles();
    }
}
