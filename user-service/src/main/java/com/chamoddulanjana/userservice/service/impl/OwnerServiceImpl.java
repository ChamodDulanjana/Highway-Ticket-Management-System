package com.chamoddulanjana.userservice.service.impl;

import com.chamoddulanjana.userservice.dto.OwnerDTO;
import com.chamoddulanjana.userservice.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {
    @Override
    public void registerOwner(OwnerDTO owner) {

    }

    @Override
    public void updateOwner(OwnerDTO owner, String identityNumber) {

    }

    @Override
    public OwnerDTO getOwnerByIdentityNumber(String identityNumber) {
        return null;
    }

    @Override
    public List<OwnerDTO> getAllOwners() {
        return List.of();
    }
}
