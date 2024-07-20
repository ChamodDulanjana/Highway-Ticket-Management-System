package com.chamoddulanjana.ticketservice.entity;

import com.chamoddulanjana.ticketservice.entity.enums.PaymentStatus;
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
public class Ticket {
    @Id
    private String id;

    @Column(nullable = false, length = 20)
    private String ticketNumber;

    @Column(nullable = false, length = 20)
    private String entranceTerminal;

    @Column(nullable = false, length = 20)
    private String exitTerminal;

    @Column(nullable = false, length = 20)
    private LocalDateTime date;

    @Column(nullable = false, length = 10)
    private double amount;

    @Enumerated(value = EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Column(nullable = false, length = 20)
    private String vehicleId;
}
