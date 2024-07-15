package com.chamoddulanjana.userservice.repository;

import com.chamoddulanjana.userservice.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, String> {
    Optional<Owner> findOwnerByIdentityNumber(String email);
}
