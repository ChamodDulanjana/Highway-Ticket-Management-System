package com.chamoddulanjana.ticketservice.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.List;

public class TicketDTO {

    @NotNull(message = "Id is required")
    @NotEmpty(message = "Id is required")
    @Length(min = 3, max = 20, message = "Id must be between 3 and 30 characters")
    private String id;

    @NotNull(message = "Entrance is required")
    @NotEmpty(message = "Entrance is required")
    @Length(min = 3, max = 20, message = "Entrance must be between 3 and 20 characters")
    private String entrance;

    @NotNull(message = "Exit is required")
    @NotEmpty(message = "Exit is required")
    @Length(min = 3, max = 20, message = "Exit must be between 3 and 20 characters")
    private String exit;

    @NotNull(message = "Date is required")
    @NotEmpty(message = "Date is required")
    @Length(min = 3, max = 20, message = "Date must be between 3 and 20 characters")
    private LocalDateTime date;

    @NotNull(message = "Payment id is required")
    @NotEmpty(message = "Payment id is required")
    @Length(min = 3, max = 10, message = "Payment id must be between 3 and 10 characters")
    private String paymentId;

    private List<VehicleDTO> vehicles;
}
