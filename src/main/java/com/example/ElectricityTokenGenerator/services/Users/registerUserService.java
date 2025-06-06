package com.example.ElectricityTokenGenerator.services.Users;

import com.example.ElectricityTokenGenerator.enums.Role;


import com.example.ElectricityTokenGenerator.entity.Users.User;
import com.example.ElectricityTokenGenerator.repository.Users.userRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
public class registerUserService {

    private final userRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public registerUserService(userRepository userRepository,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User createUser(String firstName, String lastName, String password,
            String email, String phoneNumber, String homeAddress, Role role) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setHomeAddress(homeAddress);
        user.setRole(role);
        user.setAccountNumber(generateUniqueAccountNumber());
        return userRepository.save(user);
    }

    private String generateUniqueAccountNumber() {
        String accountNumber;
        Random random = new Random();

        do {
            accountNumber = String.format("%010d", random.nextInt(1000000000));
        } while (userRepository.existsByAccountNumber(accountNumber));

        return accountNumber;
    }
}