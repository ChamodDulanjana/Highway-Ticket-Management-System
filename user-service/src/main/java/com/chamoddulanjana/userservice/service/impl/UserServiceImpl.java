package com.chamoddulanjana.userservice.service.impl;

import com.chamoddulanjana.userservice.dto.UserDTO;
import com.chamoddulanjana.userservice.entity.User;
import com.chamoddulanjana.userservice.exceptions.customExceptions.AlreadyExistException;
import com.chamoddulanjana.userservice.exceptions.customExceptions.NotFoundException;
import com.chamoddulanjana.userservice.repository.UserRepository;
import com.chamoddulanjana.userservice.service.UserService;
import com.chamoddulanjana.userservice.util.Base64Convertor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public void save(UserDTO userDto) {
        userRepository.findUserByEmail(userDto.getEmail().toLowerCase()).ifPresent( user -> {
            LOGGER.info("The User already exist: {}",userDto.getEmail());
            throw new AlreadyExistException("User already exists");
        });

        String password = Base64Convertor.convertPassword(userDto.getPassword());

        User user = User.builder()
                .email(userDto.getEmail())
                .password(password)
                .build();
        userRepository.save(user);
        LOGGER.info("The User has been saved: {}", userDto.getEmail());
    }

    @Override
    public void update(UserDTO userDto, String email) {
        if (userRepository.findUserByEmail(email).isPresent()){
            User user = userRepository.findUserByEmail(email).get();
            user.setPassword(userDto.getPassword());
            userRepository.save(user);
            LOGGER.info("The User has been updated: {}", userDto.getEmail());
        }else {
            LOGGER.info("The User does not exist: {}", email);
            throw new NotFoundException("User not found");
        }

    }
}
