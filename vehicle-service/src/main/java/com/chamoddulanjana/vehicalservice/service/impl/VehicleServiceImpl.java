package com.chamoddulanjana.vehicalservice.service.impl;

import com.chamoddulanjana.vehicalservice.dto.OwnerDTO;
import com.chamoddulanjana.vehicalservice.dto.VehicleDTO;
import com.chamoddulanjana.vehicalservice.entity.Vehicle;
import com.chamoddulanjana.vehicalservice.exceptions.customExceptions.AlreadyExistException;
import com.chamoddulanjana.vehicalservice.exceptions.customExceptions.NotFoundException;
import com.chamoddulanjana.vehicalservice.repository.VehicleRepository;
import com.chamoddulanjana.vehicalservice.service.VehicleService;
import com.chamoddulanjana.vehicalservice.util.GenerateId;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final RestTemplate restTemplate;
    private final Logger LOGGER = LoggerFactory.getLogger(VehicleServiceImpl.class);

    @Override
    public void registerVehicle(VehicleDTO vehicleDto) {

        try {
            restTemplate.getForObject("http://localhost:8080/api/v1/user/owner/id/" + vehicleDto.getOwnerId(), OwnerDTO.class, OwnerDTO.class);
        }catch (Exception exception){
            LOGGER.error(exception.getMessage());
            throw new NotFoundException(exception.getMessage());
        }

        vehicleRepository.findVehicleByLicencePlateNumber(vehicleDto.getLicencePlateNumber().toLowerCase()).ifPresent(vehicle -> {
            LOGGER.info("Vehicle already exist: {}", vehicleDto.getLicencePlateNumber());
            throw new AlreadyExistException("Vehicle already exist");
        });

        String id = GenerateId.getId("VEH").toLowerCase();

        Vehicle vehicleEntity = Vehicle.
                builder()
                .id(id)
                .brand(vehicleDto.getBrand())
                .model(vehicleDto.getModel())
                .licencePlateNumber(vehicleDto.getLicencePlateNumber())
                .color(vehicleDto.getColor())
                .seatCapacity(vehicleDto.getSeatCapacity())
                .ownerId(vehicleDto.getOwnerId())
                .build();
        vehicleRepository.save(vehicleEntity);
        LOGGER.info("Vehicle registered: {}", vehicleDto.getLicencePlateNumber());
    }

    @Override
    public VehicleDTO getVehicleByLicencePlateNumber(String licencePlateNumber) {
        LOGGER.info("Get vehicle by vehicleNumber requested: {}", licencePlateNumber);
        return vehicleRepository.findVehicleByLicencePlateNumber(licencePlateNumber.toLowerCase()).map(vehicle -> VehicleDTO.
               builder()
                .id(vehicle.getId())
                .brand(vehicle.getBrand())
                .model(vehicle.getModel())
                .licencePlateNumber(vehicle.getLicencePlateNumber())
                .color(vehicle.getColor())
                .seatCapacity(vehicle.getSeatCapacity())
                .ownerId(vehicle.getOwnerId())
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
                    .licencePlateNumber(vehicle.getLicencePlateNumber())
                    .seatCapacity(vehicle.getSeatCapacity())
                    .color(vehicle.getColor())
                    .ownerId(vehicle.getOwnerId())
                    .build();
            vehicleDTOS.add(vehicleDTO);
        }
        return vehicleDTOS;
    }

    @Override
    public void updateVehicle(VehicleDTO vehicleDto, String licencePlateNumber) {

        try {
            restTemplate.getForObject("http://localhost:8080/api/v1/user/owner/id/" + vehicleDto.getOwnerId(), OwnerDTO.class, OwnerDTO.class);
        }catch (Exception exception){
            LOGGER.error(exception.getMessage());
            throw new NotFoundException("Owner not found:{}"+vehicleDto.getOwnerId());
        }

        if (vehicleRepository.findVehicleByLicencePlateNumber(licencePlateNumber.toLowerCase()).isPresent()) {
            Vehicle vehicle = vehicleRepository.findVehicleByLicencePlateNumber(licencePlateNumber).get();
            vehicle.setBrand(vehicleDto.getBrand());
            vehicle.setModel(vehicleDto.getModel());
            vehicle.setColor(vehicleDto.getColor());
            vehicle.setSeatCapacity(vehicleDto.getSeatCapacity());

            vehicleRepository.save(vehicle);
            LOGGER.info("Vehicle updated: {}", vehicleDto.getLicencePlateNumber());
        }else {
            LOGGER.info("Vehicle not found: {}", licencePlateNumber);
            throw new NotFoundException("Vehicle not found");
        }
    }

    @Override
    public VehicleDTO getVehicleById(String vehicleId) {

        if (vehicleRepository.findById(vehicleId.toLowerCase()).isPresent()) {
            Vehicle vehicle = vehicleRepository.findById(vehicleId.toLowerCase()).get();
            VehicleDTO vehicleDTO = VehicleDTO.
                    builder()
                    .id(vehicle.getId())
                    .brand(vehicle.getBrand())
                    .model(vehicle.getModel())
                    .licencePlateNumber(vehicle.getLicencePlateNumber())
                    .seatCapacity(vehicle.getSeatCapacity())
                    .color(vehicle.getColor())
                    .ownerId(vehicle.getOwnerId())
                    .build();
            LOGGER.info("Vehicle found by Id: {}", vehicleId);
            return vehicleDTO;
        }else {
            LOGGER.info("Vehicle not found by Id: {}", vehicleId);
            throw new NotFoundException("Vehicle not found");
        }
    }
}
