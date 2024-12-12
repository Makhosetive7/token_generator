package com.example.ElectricityTokenGenerator.services.Users;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ElectricityTokenGenerator.entity.UsersEntity;
import com.example.ElectricityTokenGenerator.repository.UserRepository;


@Service
public class returnAllUserService {
    
    public final UserRepository userRepository;

    public returnAllUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

        //return all users
    public List<UsersEntity> getAllUsers() {
        return userRepository.findAll();
    }
}
