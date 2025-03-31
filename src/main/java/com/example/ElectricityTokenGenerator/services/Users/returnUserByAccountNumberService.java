package com.example.ElectricityTokenGenerator.services.Users;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.ElectricityTokenGenerator.entity.Users.User;
import com.example.ElectricityTokenGenerator.repository.Users.userRepository;

@Service
public class returnUserByAccountNumberService {
    

    private final userRepository userRepository;

    public returnUserByAccountNumberService(userRepository userRepository) {
        this.userRepository = userRepository;
    }

        //return user by account number
    public Optional<User> getUserByAccountNumber(String accountNumber) {
         return userRepository.findByAccountNumber(accountNumber); }

}
