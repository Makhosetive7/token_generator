package com.example.ElectricityTokenGenerator.services;

import java.util.List;
import java.util.Optional;

import com.example.ElectricityTokenGenerator.entity.UsersEntity; // Change to entity package
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ElectricityTokenGenerator.repository.UserRepository;

import java.util.Random;

@Service
public class UserServices {

    private final UserRepository userRepository;

    @Autowired
    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // register user
    public UsersEntity createUser(String userName,String lastName, String phoneNumber, String homeAddress ) {
      UsersEntity  user = new UsersEntity();
        user.setUserName(userName);
        user.setLastName(lastName);
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

    //return all users
    public List<UsersEntity> getAllUsers() {
        return userRepository.findAll();
    }

    // return user by user id
    public Optional<UsersEntity> getUserById(Long id) {
        return userRepository.findById(id);
    }

    //return user by account number
    public Optional<UsersEntity> getUserByAccountNumber(String accountNumber) {
         return userRepository.findByAccountNumber(accountNumber); }

    // Delete a user by ID
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}