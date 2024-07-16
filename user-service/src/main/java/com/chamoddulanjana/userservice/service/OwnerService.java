package com.chamoddulanjana.userservice.service;



import com.chamoddulanjana.userservice.dto.OwnerDTO;

import java.util.List;

public interface OwnerService {
    void registerOwner(OwnerDTO owner);
    void updateOwner(OwnerDTO owner, String identityNumber);
    OwnerDTO getOwnerByIdentityNumber(String identityNumber);
    OwnerDTO getOwnerById(String id);
    List<OwnerDTO> getAllOwners();
}
