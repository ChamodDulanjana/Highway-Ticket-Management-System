package com.chamoddulanjana.ticketservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Vehicle implements Serializable {
    @Id
    @Column(length = 20)
    private String id;

    @Column(length = 30, nullable = false)
    private String brand;

    @Column(length = 50, nullable = false)
    private String model;

    @Column(length = 10, nullable = false, unique = true)
    private String licencePlateNumber;

    @Column(length = 20, nullable = false)
    private String color;

    @Column(nullable = false)
    private int seatCapacity;

    @OneToMany
    private List<Ticket> ticketList;

}
