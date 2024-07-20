package com.chamoddulanjana.paymentservice.entity;

import com.chamoddulanjana.paymentservice.entity.enums.PaymentMethod;
import com.chamoddulanjana.paymentservice.entity.enums.PaymentStatus;
import jakarta.persistence.*;
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

    @Column(nullable = false, length = 50)
    public LocalDateTime dateTime;

    @Column(nullable = false, length = 50)
    private double amount;

    @Enumerated(value = EnumType.STRING)

    @Column(nullable = false, length = 50)
    private PaymentMethod paymentMethod;

    @Column(nullable = false, length = 50)
    private String ticketId;
}
