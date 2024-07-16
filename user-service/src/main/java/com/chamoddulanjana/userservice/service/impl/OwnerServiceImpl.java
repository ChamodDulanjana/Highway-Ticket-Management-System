package com.chamoddulanjana.userservice.service.impl;

import com.chamoddulanjana.userservice.dto.OwnerDTO;
import com.chamoddulanjana.userservice.entity.Owner;
import com.chamoddulanjana.userservice.exceptions.customExceptions.AlreadyExistException;
import com.chamoddulanjana.userservice.exceptions.customExceptions.NotFoundException;
import com.chamoddulanjana.userservice.repository.OwnerRepository;
import com.chamoddulanjana.userservice.service.OwnerService;
import com.chamoddulanjana.userservice.util.GenerateId;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final Logger LOGGER = LoggerFactory.getLogger(OwnerServiceImpl.class);

    @Override
    public void registerOwner(OwnerDTO owner) {
        ownerRepository.findOwnerByIdentityNumber(owner.getIdentityNumber().toLowerCase()).ifPresent(Owner ->{
            LOGGER.info("Owner already exist: {}", owner.getIdentityNumber());
            throw new AlreadyExistException("Owner already exist");
        });

        String id = GenerateId.getId("OWN").toLowerCase();

        Owner ownerEntity = Owner.
                builder()
                .id(id)
                .name(owner.getName())
                .identityNumber(owner.getIdentityNumber())
                .phoneNumber(owner.getPhoneNumber())
                .address(owner.getAddress())
                .email(owner.getEmail())
                .build();

        ownerRepository.save(ownerEntity);
        LOGGER.info("Owner registered: {}", ownerEntity.getIdentityNumber());
    }

    @Override
    public void updateOwner(OwnerDTO ownerDTO, String identityNumber) {
        Owner owner = ownerRepository.findOwnerByIdentityNumber(identityNumber.toLowerCase()).orElseThrow(() -> new NotFoundException("This owner does not exist"));
        owner.setName(ownerDTO.getName());
        owner.setPhoneNumber(ownerDTO.getPhoneNumber());
        owner.setAddress(ownerDTO.getAddress());
        owner.setEmail(ownerDTO.getEmail());
        ownerRepository.save(owner);
        LOGGER.info("Owner updated: {}", owner.getIdentityNumber());
    }

    @Override
    public OwnerDTO getOwnerByIdentityNumber(String identityNumber) {
        LOGGER.info("Get owner by identity number: {}", identityNumber);
        return ownerRepository.findOwnerByIdentityNumber(identityNumber.toLowerCase()).map(owner -> OwnerDTO.
                builder()
                .name(owner.getName())
                .address(owner.getAddress())
                .email(owner.getEmail())
                .phoneNumber(owner.getPhoneNumber())
                .identityNumber(identityNumber)
                .build()
        ).orElseThrow(() -> new NotFoundException("This owner does not exist"));
    }

    @Override
    public List<OwnerDTO> getAllOwners() {
        LOGGER.info("Get all owners");
        return ownerRepository.findAll().stream().map(owner -> OwnerDTO.
                builder()
                .id(owner.getId())
                .name(owner.getName())
                .phoneNumber(owner.getPhoneNumber())
                .identityNumber(owner.getIdentityNumber())
                .address(owner.getAddress())
                .email(owner.getEmail())
                .build()).toList();
    }
}
