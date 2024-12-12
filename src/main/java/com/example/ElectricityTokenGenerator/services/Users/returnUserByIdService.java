package com.example.ElectricityTokenGenerator.services.Users;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.ElectricityTokenGenerator.entity.UsersEntity;
import com.example.ElectricityTokenGenerator.repository.UserRepository;

@Service
public class returnUserByIdService {

    private final UserRepository userRepository;


    public returnUserByIdService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    // return user by user id
    public Optional<UsersEntity> getUserById(Long id) {
        return userRepository.findById(id);
    }


}

