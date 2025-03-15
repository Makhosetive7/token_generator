package com.example.ElectricityTokenGenerator.controllers.UserController;

import com.example.ElectricityTokenGenerator.dto.Users.UserRegistrationDTO;
import com.example.ElectricityTokenGenerator.entity.Users.User;
import com.example.ElectricityTokenGenerator.services.Users.registerUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/users")
public class registerUserController {

    private final registerUserService registerUserService;

    public registerUserController(registerUserService registerUserService) {
        this.registerUserService = registerUserService;
    }

    /**
     * Endpoint to register a new user.
     *
     * @param firstName   The first name of the user.
     * @param lastName    The last name of the user.
     * @param password    The password of the user.
     * @param email       The email of the user.
     * @param phoneNumber The phone number of the user.
     * @param homeAddress The home address of the user.
     * @param createdAt   The date and time of registration.
     * @return The created user as a ResponseEntity.
     */
    @PostMapping("/register")
    public ResponseEntity<User> createUser(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String password,
            @RequestParam String email,
            @RequestParam String phoneNumber,
            @RequestParam String homeAddress,
            @RequestParam LocalDateTime createdAt) {

        try {
            // Create a UserRegistrationDTO object
            UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO();
            userRegistrationDTO.setFirstName(firstName);
            userRegistrationDTO.setLastName(lastName);
            userRegistrationDTO.setPassword(password);
            userRegistrationDTO.setEmail(email);
            userRegistrationDTO.setPhoneNumber(phoneNumber);
            userRegistrationDTO.setHomeAddress(homeAddress);
            userRegistrationDTO.setCreatedAt(createdAt);

            // Call the service to register the user
            User user = registerUserService.createUser(userRegistrationDTO);

            // Return the created user with a 201 CREATED status
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        } catch (RuntimeException e) {
            // Handle exceptions (e.g., user already exists)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}