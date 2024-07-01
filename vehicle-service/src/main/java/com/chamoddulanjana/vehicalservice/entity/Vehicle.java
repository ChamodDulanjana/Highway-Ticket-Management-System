package com.chamoddulanjana.vehicalservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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

    @Column(length = 20, nullable = false)
    private String color;

    @Column(nullable = false)
    private int seatCapacity;

    @Column(length = 100, nullable = false)
    private String ownerName;

    @Column(nullable = false, length = 50)
    private String ownerEmail;

}
