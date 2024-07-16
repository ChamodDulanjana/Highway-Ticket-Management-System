package com.chamoddulanjana.ticketservice.entity;

import com.chamoddulanjana.ticketservice.entity.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Ticket {
    @Id
    private String ticketNumber;

    @Column(nullable = false, length = 20)
    private String entrance;

    @Column(nullable = false, length = 20)
    private String exit;

    @Column(nullable = false, length = 20)
    private LocalDateTime date;

    @Column(nullable = false, length = 10)
    private double amount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Column(nullable = false, length = 20)
    private String vehicleId;
}
