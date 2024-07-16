package com.chamoddulanjana.vehicalservice.service;

import com.chamoddulanjana.vehicalservice.dto.VehicleDTO;

import java.util.List;

public interface VehicleService {
    void registerVehicle(VehicleDTO vehicle);
    VehicleDTO getVehicleByLicencePlateNumber(String licencePlateNumber);
    List<VehicleDTO> getAllVehicles();
    void updateVehicle(VehicleDTO vehicle, String vehicleNumber);
    VehicleDTO getVehicleById(String vehicleId);
}
