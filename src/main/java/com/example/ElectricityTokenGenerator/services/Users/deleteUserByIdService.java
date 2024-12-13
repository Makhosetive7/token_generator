package com.example.ElectricityTokenGenerator.services.Users;

import org.springframework.stereotype.Service;

import com.example.ElectricityTokenGenerator.repository.Users.userRepository;

@Service
public class deleteUserByIdService {

    private final userRepository userRepository;

    public deleteUserByIdService(userRepository userRepository) {
        this.userRepository = userRepository;
    }

        // Delete a user by ID
        public void deleteUser(Long id) {
            userRepository.deleteById(id);
        }

    
}
