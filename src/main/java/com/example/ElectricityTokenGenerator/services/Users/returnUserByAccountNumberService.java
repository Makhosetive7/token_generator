package com.example.ElectricityTokenGenerator.services.Users;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.ElectricityTokenGenerator.entity.UsersEntity;
import com.example.ElectricityTokenGenerator.repository.UserRepository;

@Service
public class returnUserByAccountNumberService {
    

    private final UserRepository userRepository;

    public returnUserByAccountNumberService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

        //return user by account number
    public Optional<UsersEntity> getUserByAccountNumber(String accountNumber) {
         return userRepository.findByAccountNumber(accountNumber); }

}
