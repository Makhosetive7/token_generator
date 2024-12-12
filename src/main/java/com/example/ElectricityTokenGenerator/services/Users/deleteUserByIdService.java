package com.example.ElectricityTokenGenerator.services.Users;

import org.springframework.stereotype.Service;

import com.example.ElectricityTokenGenerator.repository.UserRepository;

@Service
public class deleteUserByIdService {

    private final UserRepository userRepository;

    public deleteUserByIdService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

        // Delete a user by ID
        public void deleteUser(Long id) {
            userRepository.deleteById(id);
        }

    
}
