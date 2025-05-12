package com.example.ElectricityTokenGenerator.services.Users;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ElectricityTokenGenerator.entity.Users.User;
import com.example.ElectricityTokenGenerator.exceptionHandling.UsersException.InvalidPasswordException;
import com.example.ElectricityTokenGenerator.exceptionHandling.UsersException.UserNotFoundException;
import com.example.ElectricityTokenGenerator.repository.Users.userRepository;

@Service
public class loginUserService {

    private final userRepository userRepository; 
    private final PasswordEncoder passwordEncoder;


    public loginUserService(userRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User loginUser(String email, String password) {
        // check user availabilty from database
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(": " + email));
                
        // check if the password is correct
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidPasswordException(": " + password);
        }

        return user;
    }
}