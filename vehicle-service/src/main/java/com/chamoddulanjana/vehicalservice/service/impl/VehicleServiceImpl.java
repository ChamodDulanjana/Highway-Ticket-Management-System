package com.chamoddulanjana.vehicalservice.service.impl;

import com.chamoddulanjana.vehicalservice.dto.OwnerDTO;
import com.chamoddulanjana.vehicalservice.dto.VehicleDTO;
import com.chamoddulanjana.vehicalservice.entity.Owner;
import com.chamoddulanjana.vehicalservice.entity.Vehicle;
import com.chamoddulanjana.vehicalservice.exceptions.customExceptions.AlreadyExistException;
import com.chamoddulanjana.vehicalservice.exceptions.customExceptions.NotFoundException;
import com.chamoddulanjana.vehicalservice.repository.VehicleRepository;
import com.chamoddulanjana.vehicalservice.service.VehicleService;
import com.chamoddulanjana.vehicalservice.util.GenerateId;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
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

        ResponseEntity<OwnerDTO> forEntity = restTemplate.getForEntity("http://localhost:8080/api/v1/owner" + vehicleDto.getOwnerDTO().getId(), OwnerDTO.class);
        OwnerDTO ownerDTO = forEntity.getBody();
        if (ownerDTO == null) {
            throw new NotFoundException("Owner not found");
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
                .owner(
                        Owner.builder()
                                .id(ownerDTO.getId())
                                .name(ownerDTO.getName())
                                .identityNumber(ownerDTO.getIdentityNumber())
                                .phoneNumber(ownerDTO.getPhoneNumber())
                                .address(ownerDTO.getAddress())
                                .email(ownerDTO.getEmail())
                                .build()
                )
                .build();
        vehicleRepository.save(vehicleEntity);
        LOGGER.info("Vehicle registered: {}", vehicleDto.getLicencePlateNumber());
    }

    @Override
    public VehicleDTO getVehicleById(String vehicleNumber) {
        LOGGER.info("Get vehicle by vehicleNumber requested: {}", vehicleNumber);
        return vehicleRepository.findVehicleByLicencePlateNumber(vehicleNumber.toLowerCase()).map(vehicle -> VehicleDTO.
               builder()
                .id(vehicle.getId())
                .brand(vehicle.getBrand())
                .model(vehicle.getModel())
                .licencePlateNumber(vehicle.getLicencePlateNumber())
                .color(vehicle.getColor())
                .seatCapacity(vehicle.getSeatCapacity())
                .ownerDTO(
                        OwnerDTO.builder()
                                .id(vehicle.getOwner().getId())
                                .name(vehicle.getOwner().getName())
                                .identityNumber(vehicle.getOwner().getIdentityNumber())
                                .phoneNumber(vehicle.getOwner().getPhoneNumber())
                                .address(vehicle.getOwner().getAddress())
                                .email(vehicle.getOwner().getEmail())
                                .build()
                )
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
                    .ownerDTO(
                            OwnerDTO.builder()
                                    .id(vehicle.getOwner().getId())
                                    .name(vehicle.getOwner().getName())
                                    .identityNumber(vehicle.getOwner().getIdentityNumber())
                                    .phoneNumber(vehicle.getOwner().getPhoneNumber())
                                    .address(vehicle.getOwner().getAddress())
                                    .email(vehicle.getOwner().getEmail())
                                    .build()
                    )
                    .build();
            vehicleDTOS.add(vehicleDTO);
        }
        return vehicleDTOS;
    }

    @Override
    public void updateVehicle(VehicleDTO vehicleDto, String licenceNumber) {

        ResponseEntity<OwnerDTO> forEntity = restTemplate.getForEntity("http://localhost:8080/api/v1/owner" + vehicleDto.getOwnerDTO().getId(), OwnerDTO.class);
        OwnerDTO ownerDTO = forEntity.getBody();
        if (ownerDTO == null) {
            throw new NotFoundException("Owner not found");
        }

        if (vehicleRepository.findVehicleByLicencePlateNumber(licenceNumber.toLowerCase()).isPresent()) {
            Vehicle vehicle = vehicleRepository.findVehicleByLicencePlateNumber(licenceNumber).get();
            vehicle.setBrand(vehicleDto.getBrand());
            vehicle.setModel(vehicleDto.getModel());
            vehicle.setColor(vehicleDto.getColor());
            vehicle.setSeatCapacity(vehicleDto.getSeatCapacity());
            vehicle.setOwner(
                    Owner.builder()
                            .id(ownerDTO.getId())
                            .name(ownerDTO.getName())
                            .identityNumber(ownerDTO.getIdentityNumber())
                            .phoneNumber(ownerDTO.getPhoneNumber())
                            .address(ownerDTO.getAddress())
                            .email(ownerDTO.getEmail())
                            .build()
            );
            vehicleRepository.save(vehicle);
            LOGGER.info("Vehicle updated: {}", vehicleDto.getLicencePlateNumber());
        }else {
            LOGGER.info("Vehicle not found: {}", licenceNumber);
            throw new NotFoundException("Vehicle not found");
        }
    }
}
