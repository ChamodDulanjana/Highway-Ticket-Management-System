package com.chamoddulanjana.vehicalservice.service.impl;

import com.chamoddulanjana.vehicalservice.dto.VehicleDTO;
import com.chamoddulanjana.vehicalservice.entity.Vehicle;
import com.chamoddulanjana.vehicalservice.repository.VehicleRepository;
import com.chamoddulanjana.vehicalservice.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    @Override
    public void registerVehicle(VehicleDTO vehicle) {
        Vehicle vehicleEntity = Vehicle.
                builder()
                .id(vehicle.getId())
                .brand(vehicle.getBrand())
                .model(vehicle.getModel())
                .color(vehicle.getColor())
                .seatCapacity(vehicle.getSeatCapacity())
                .ownerName(vehicle.getOwnerName())
                .ownerEmail(vehicle.getOwnerEmail())
                .build();
        vehicleRepository.save(vehicleEntity);
    }

    @Override
    public VehicleDTO getVehicleById(String id) {
        return vehicleRepository.findById(id).map(vehicle -> VehicleDTO.
               builder()
                .id(vehicle.getId())
               .brand(vehicle.getBrand())
               .model(vehicle.getModel())
               .color(vehicle.getColor())
               .seatCapacity(vehicle.getSeatCapacity())
               .ownerName(vehicle.getOwnerName())
               .ownerEmail(vehicle.getOwnerEmail())
               .build()
       ).orElseThrow(() -> new RuntimeException("Vehicle not found"));
    }

    @Override
    public List<VehicleDTO> getAllVehicles() {
        List<Vehicle> all= vehicleRepository.findAll();
        List<VehicleDTO> vehicleDTOS = new ArrayList<>();
        for (Vehicle vehicle : all) {
            VehicleDTO vehicleDTO = VehicleDTO.
                    builder()
                    .id(vehicle.getId())
                    .brand(vehicle.getBrand())
                    .model(vehicle.getModel())
                    .seatCapacity(vehicle.getSeatCapacity())
                    .color(vehicle.getColor())
                    .ownerName(vehicle.getOwnerName())
                    .ownerEmail(vehicle.getOwnerEmail())
                    .build();
            vehicleDTOS.add(vehicleDTO);
        }
        return vehicleDTOS;
    }

    @Override
    public void updateVehicle(VehicleDTO vehicleDto, String id) {
        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(() -> new RuntimeException("Vehicle not found"));
        vehicle.setBrand(vehicleDto.getBrand());
        vehicle.setModel(vehicleDto.getModel());
        vehicle.setColor(vehicleDto.getColor());
        vehicle.setSeatCapacity(vehicleDto.getSeatCapacity());
        vehicle.setOwnerName(vehicleDto.getOwnerName());
        vehicle.setOwnerEmail(vehicleDto.getOwnerEmail());
        vehicleRepository.save(vehicle);

    }
}
