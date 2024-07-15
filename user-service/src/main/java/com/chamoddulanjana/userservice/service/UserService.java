package com.chamoddulanjana.userservice.service;

import com.chamoddulanjana.userservice.dto.UserDTO;

import java.io.Serializable;
import java.util.List;

public interface UserService extends Serializable {
    void save(UserDTO user);
    void update(UserDTO user, String email);
    UserDTO findByEmail(String email);
    List<UserDTO> findAll();
}
