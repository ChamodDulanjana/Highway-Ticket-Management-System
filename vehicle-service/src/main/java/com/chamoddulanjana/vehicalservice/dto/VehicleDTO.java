package com.chamoddulanjana.vehicalservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class VehicleDTO implements Serializable {
    private String id;

    @NotEmpty(message = "Brand is required")
    @Length(min = 3, max = 30, message = "Brand must be between 3 and 30 characters")
    private String brand;

    @NotEmpty(message = "Model is required")
    @Length(min = 3, max = 50, message = "Model must be between 3 and 50 characters")
    private String model;

    @NotEmpty(message = "Vehicle number is required")
    @Length(min = 3, max = 10, message = "Vehicle number must be between 3 and 10 characters")
    private String vehicleNumber;

    @NotEmpty(message = "Color is required")
    @Length(min = 3, max = 20, message = "Color must be between 3 and 20 characters")
    private String color;

    @NotEmpty(message = "Seat capacity is required")
    private int  seatCapacity;

    @NotEmpty(message = "Owner name is required")
    @Length(min = 3, max = 100, message = "Owner name must be between 3 and 100 characters")
    private String ownerName;

    @NotEmpty(message = "Owner email is required")
    @Email(message = "Owner email should be valid")
    private String ownerEmail;

}
