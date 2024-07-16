package com.chamoddulanjana.userservice.controller;

import com.chamoddulanjana.userservice.dto.OwnerDTO;
import com.chamoddulanjana.userservice.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user/owner")
@RequiredArgsConstructor
public class OwnerController {

    private final OwnerService ownerService;
    private final Logger LOGGER = LoggerFactory.getLogger(OwnerController.class);

    @GetMapping("/health")
    public String healthCheck(){
        return "Owner health check";
    }

    @PostMapping
    public void registerOwner(@RequestBody OwnerDTO owner){
        LOGGER.info("Registering owner request: {}", owner);
        ownerService.registerOwner(owner);
    }

    @PutMapping
    public void updateOwner(@RequestBody OwnerDTO owner, @RequestParam String identityNumber){
        LOGGER.info("Updating owner request: {}", owner.getIdentityNumber());
        ownerService.updateOwner(owner, identityNumber);
    }

    @GetMapping("/{identityNumber}")
    public OwnerDTO getOwnerByIdentityNumber(@PathVariable String identityNumber){
        LOGGER.info("Retrieving owner by identity number request: {}", identityNumber);
        return ownerService.getOwnerByIdentityNumber(identityNumber);
    }

    @GetMapping("/getAll")
    public List<OwnerDTO> getAllOwners(){
        LOGGER.info("Retrieving all owners request");
        return ownerService.getAllOwners();
    }

    @GetMapping("/id/{id}")
    public OwnerDTO getOwnerById(@PathVariable String id){
        LOGGER.info("Retrieving owner by id request: {}", id);
        return ownerService.getOwnerById(id);
    }
}
