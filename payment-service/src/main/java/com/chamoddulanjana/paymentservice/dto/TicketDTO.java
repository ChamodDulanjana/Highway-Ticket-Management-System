package com.chamoddulanjana.paymentservice.dto;

import com.chamoddulanjana.paymentservice.entity.enums.PaymentStatus;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TicketDTO {

    @NotNull(message = "Id is required")
    @NotEmpty(message = "Id is required")
    @Length(min = 3, max = 30, message = "Id must be between 3 and 30 characters")
    private String ticketNumber;

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

    @NotNull(message = "Amount is required")
    @NotEmpty(message = "Amount is required")
    @Length(min = 1, max = 10, message = "Amount must be between 1 and 10 characters")
    private double amount;

    @NotNull(message = "PaymentStatus is required")
    @NotEmpty(message = "PaymentStatus is required")
    private PaymentStatus paymentStatus;

    private String vehicleId;
}
