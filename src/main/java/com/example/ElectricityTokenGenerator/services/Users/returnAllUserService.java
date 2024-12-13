package com.example.ElectricityTokenGenerator.services.Users;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ElectricityTokenGenerator.entity.Users.UserEntities;
import com.example.ElectricityTokenGenerator.repository.Users.userRepository;


@Service
public class returnAllUserService {
    
    private final userRepository userRepository;

    public returnAllUserService(userRepository userRepository) {
        this.userRepository = userRepository;
    }

        //return all users
    public List<UserEntities> getAllUsers() {
        return userRepository.findAll();
    }
}
