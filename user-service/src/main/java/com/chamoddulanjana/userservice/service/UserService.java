package com.chamoddulanjana.userservice.service;

import com.chamoddulanjana.userservice.dto.UserDTO;

import java.io.Serializable;

public interface UserService extends Serializable {
    void save(UserDTO user);
    void update(UserDTO user, String email);
}
