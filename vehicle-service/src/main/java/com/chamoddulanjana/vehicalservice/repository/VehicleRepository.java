package com.chamoddulanjana.vehicalservice.repository;

import com.chamoddulanjana.vehicalservice.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, String> {

    Optional<Vehicle> findVehicleByLicencePlateNumber(String licencePlateNumber);
}
