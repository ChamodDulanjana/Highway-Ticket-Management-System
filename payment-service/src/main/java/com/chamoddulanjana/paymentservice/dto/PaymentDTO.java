package com.chamoddulanjana.paymentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PaymentDTO {
    private String id;
    public LocalDateTime dateTime;
    private double amount;
    private String ticketNumber;
}
