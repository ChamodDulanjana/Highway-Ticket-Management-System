package com.chamoddulanjana.vehicalservice.service.impl;

import com.chamoddulanjana.vehicalservice.dto.VehicleDTO;
import com.chamoddulanjana.vehicalservice.entity.Vehicle;
import com.chamoddulanjana.vehicalservice.exceptions.customExceptions.AlreadyExistException;
import com.chamoddulanjana.vehicalservice.exceptions.customExceptions.NotFoundException;
import com.chamoddulanjana.vehicalservice.repository.VehicleRepository;
import com.chamoddulanjana.vehicalservice.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final Logger LOGGER = LoggerFactory.getLogger(VehicleServiceImpl.class);

    @Override
    public void registerVehicle(VehicleDTO vehicleDto) {

        vehicleRepository.findVehicleByVehicleNumber(vehicleDto.getVehicleNumber().toLowerCase()).ifPresent(vehicle -> {
            LOGGER.info("Vehicle already exist: {}", vehicleDto.getVehicleNumber());
            throw new AlreadyExistException("Vehicle already exist");
        });

        Vehicle vehicleEntity = Vehicle.
                builder()
                .id(vehicleDto.getId())
                .brand(vehicleDto.getBrand())
                .model(vehicleDto.getModel())
                .color(vehicleDto.getColor())
                .seatCapacity(vehicleDto.getSeatCapacity())
                .ownerName(vehicleDto.getOwnerName())
                .ownerEmail(vehicleDto.getOwnerEmail())
                .build();
        vehicleRepository.save(vehicleEntity);
        LOGGER.info("Vehicle registered: {}", vehicleDto.getVehicleNumber());
    }

    @Override
    public VehicleDTO getVehicleById(String id) {
        LOGGER.info("Get vehicle by id requested: {}", id);
        return vehicleRepository.findById(id).map(vehicle -> VehicleDTO.
               builder()
                .id(vehicle.getId())
                .brand(vehicle.getBrand())
                .model(vehicle.getModel())
                .vehicleNumber(vehicle.getVehicleNumber())
                .color(vehicle.getColor())
                .seatCapacity(vehicle.getSeatCapacity())
                .ownerName(vehicle.getOwnerName())
                .ownerEmail(vehicle.getOwnerEmail())
                .build()
       ).orElseThrow(() -> new NotFoundException("Vehicle not found"));
    }

    @Override
    public List<VehicleDTO> getAllVehicles() {
        LOGGER.info("Get all vehicles requested");
        List<Vehicle> all= vehicleRepository.findAll();
        List<VehicleDTO> vehicleDTOS = new ArrayList<>();
        for (Vehicle vehicle : all) {
            VehicleDTO vehicleDTO = VehicleDTO.
                    builder()
                    .id(vehicle.getId())
                    .brand(vehicle.getBrand())
                    .model(vehicle.getModel())
                    .vehicleNumber(vehicle.getVehicleNumber())
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
        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(() -> new NotFoundException("Vehicle not found"));
        vehicle.setBrand(vehicleDto.getBrand());
        vehicle.setModel(vehicleDto.getModel());
        vehicle.setColor(vehicleDto.getColor());
        vehicle.setSeatCapacity(vehicleDto.getSeatCapacity());
        vehicle.setOwnerName(vehicleDto.getOwnerName());
        vehicle.setOwnerEmail(vehicleDto.getOwnerEmail());
        vehicleRepository.save(vehicle);
        LOGGER.info("Vehicle updated: {}", vehicleDto.getVehicleNumber());
    }
}