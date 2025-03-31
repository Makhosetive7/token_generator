package com.example.ElectricityTokenGenerator.services.Users;

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
    public User createUser(String firstName,String lastName,String password, String email , String phoneNumber, String homeAddress) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(password);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setHomeAddress(homeAddress);
        user.setAccountNumber(generateUniqueAccountNumber());
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