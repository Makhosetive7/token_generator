package com.example.ElectricityTokenGenerator.services;

import java.util.List;
import java.util.Optional;

import com.example.ElectricityTokenGenerator.entity.UsersEntity; // Change to entity package
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ElectricityTokenGenerator.repository.UserRepository;

@Service
public class UserServices {
    
    private final UserRepository userRepository;

    @Autowired
    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Create a new user
    public UsersEntity createUser(UsersEntity user) {
        return userRepository.save(user);
    }

    // Get a user by ID
    public Optional<UsersEntity> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Get all users
    public List<UsersEntity> getAllUsers() {
        return userRepository.findAll();
    }

    // Delete a user by ID
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
