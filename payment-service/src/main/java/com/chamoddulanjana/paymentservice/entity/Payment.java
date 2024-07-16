package com.chamoddulanjana.paymentservice.entity;

import com.chamoddulanjana.paymentservice.entity.enums.PaymentMethod;
import com.chamoddulanjana.paymentservice.entity.enums.PaymentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Payment {
    @Id
    private String id;
    public LocalDateTime dateTime;
    private double amount;
    @Enumerated(value = EnumType.STRING)
    private PaymentMethod paymentMethod;
    private String ticketNumber;
}
