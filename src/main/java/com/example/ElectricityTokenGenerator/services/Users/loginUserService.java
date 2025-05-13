package com.example.ElectricityTokenGenerator.services.Users;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ElectricityTokenGenerator.dto.Users.AuthResponseDTO;
import com.example.ElectricityTokenGenerator.entity.Users.User;
import com.example.ElectricityTokenGenerator.exceptionHandling.UsersException.InvalidPasswordException;
import com.example.ElectricityTokenGenerator.exceptionHandling.UsersException.UserNotFoundException;
import com.example.ElectricityTokenGenerator.repository.Users.userRepository;
import com.example.ElectricityTokenGenerator.utils.JWTUtil;

@Service
public class loginUserService {

    private final userRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public loginUserService(userRepository userRepository, PasswordEncoder passwordEncoder, JWTUtil jwtUtil,
            AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthResponseDTO loginUser(String email, String password) {
        // check user availabilty from database
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));

        // check if the password is correct
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidPasswordException(": " + password);
        }

        // Authenticate using Spring Security's AuthenticationManager
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password));

        String accessToken = jwtUtil.generateToken(user);
        String refreshToken = jwtUtil.generateRefreshToken(user);

        return AuthResponseDTO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}