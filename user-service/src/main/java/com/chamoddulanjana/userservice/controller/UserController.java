package com.chamoddulanjana.userservice.controller;

import com.chamoddulanjana.userservice.dto.UserDTO;
import com.chamoddulanjana.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @GetMapping
    public String healthCheck(){
        return "User Health Check";
    }

    @PostMapping
    public void registerUser(@RequestBody UserDTO user){
        LOGGER.info("Registering user request: {}", user);
        userService.save(user);
    }

    @PutMapping
    public void updateUser(@RequestBody UserDTO user, @RequestParam String email){
        LOGGER.info("Updating user request: {}", user.getEmail());
        userService.update(user, email);
    }

    @GetMapping("/{email}")
    public UserDTO getUserByEmail(@PathVariable String email){
        LOGGER.info("Retrieving user by email: {}", email);
        return userService.findByEmail(email);
    }

    @GetMapping("/getAll")
    public List<UserDTO> getAllUsers(){
        LOGGER.info("Retrieving all users");
        return userService.findAll();
    }
}
