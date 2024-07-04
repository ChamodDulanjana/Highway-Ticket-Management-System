package com.chamoddulanjana.ticketservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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
    private String id;

    @Column(nullable = false, length = 20)
    private String entrance;

    @Column(nullable = false, length = 20)
    private String exit;

    @Column(nullable = false, length = 20)
    private LocalDateTime date;

    @Column(nullable = false, length = 10)
    private String paymentId;


    //private List<Vehicle> vehicles;
}
