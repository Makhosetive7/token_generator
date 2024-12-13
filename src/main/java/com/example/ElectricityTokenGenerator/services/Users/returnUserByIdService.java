package com.example.ElectricityTokenGenerator.services.Users;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.ElectricityTokenGenerator.entity.Users.UserEntities;
import com.example.ElectricityTokenGenerator.repository.Users.userRepository;

@Service
public class returnUserByIdService {

    private final userRepository userRepository;


    public returnUserByIdService(userRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    // return user by user id
    public Optional<UserEntities> getUserById(Long id) {
        return userRepository.findById(id);
    }


}

