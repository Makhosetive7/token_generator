package com.example.ElectricityTokenGenerator.services.Users;

import com.example.ElectricityTokenGenerator.dto.Users.UserRegistrationDTO;
import com.example.ElectricityTokenGenerator.entity.Users.User;
import com.example.ElectricityTokenGenerator.repository.Users.userRepository;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class registerUserService {

    private final userRepository userRepository;

    public registerUserService(userRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Register user
    public User createUser(UserRegistrationDTO userRegistrationDTO) {
        User user = new User();
        user.setFirstName(userRegistrationDTO.getFirstName());
        user.setLastName(userRegistrationDTO.getLastName());
        user.setPassword(userRegistrationDTO.getPassword());
        user.setEmail(userRegistrationDTO.getEmail());
        user.setPhoneNumber(userRegistrationDTO.getPhoneNumber());
        user.setAccountNumber(generateUniqueAccountNumber());
        user.setHomeAddress(userRegistrationDTO.getHomeAddress());

        return userRepository.save(user);
    }

    private String generateUniqueAccountNumber() {
        String accountNumber;
        Random random = new Random();

        // Ensure the account number is unique
        do {
            accountNumber = String.format("%010d", random.nextInt(1000000000));
        } while (userRepository.existsByAccountNumber(accountNumber));

        return accountNumber;
    }
}