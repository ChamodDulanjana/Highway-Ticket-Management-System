package com.chamoddulanjana.paymentservice.dto;

import com.chamoddulanjana.paymentservice.entity.enums.PaymentMethod;
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
public class PaymentDTO {

    @NotNull(message = "Id is required")
    @NotEmpty(message = "Id is required")
    @Length(min = 3, max = 30, message = "Id must be between 3 and 30 characters")
    private String id;

    @NotNull(message = "Date & Time is required")
    @NotEmpty(message = "Date & Time is required")
    @Length(min = 3, max = 30, message = "Date & Time must be between 3 and 30 characters")
    public LocalDateTime dateTime;

    @NotNull(message = "Amount is required")
    @NotEmpty(message = "Amount is required")
    @Length(min = 1, max = 10, message = "Amount must be between 1 and 10 characters")
    private double amount;

    @NotNull(message = "Id is required")
    @NotEmpty(message = "Id is required")
    private PaymentMethod paymentMethod;

    @NotNull(message = "Ticket Number is required")
    @NotEmpty(message = "Ticket Number is required")
    @Length(min = 3, max = 30, message = "Ticket Number must be between 3 and 30 characters")
    private String ticketNumber;

}
