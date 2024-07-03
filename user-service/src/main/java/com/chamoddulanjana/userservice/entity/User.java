package com.chamoddulanjana.userservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class User {
    @Id
    @Column(nullable = false, unique = true, length = 20)
    private String email;

    @Column(unique=true, nullable=false, length=15)
    private String password;
}
