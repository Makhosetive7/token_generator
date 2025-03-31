package com.example.ElectricityTokenGenerator.controllers.TokensController;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ElectricityTokenGenerator.entity.Tokens.Donation;
import com.example.ElectricityTokenGenerator.enums.Donations;
import com.example.ElectricityTokenGenerator.services.Tokens.DonationsServices;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("api/tokens/")
public class DonationsController {

    private final DonationsServices donationsServices;

    public DonationsController(DonationsServices donationsServices) {
        this.donationsServices = donationsServices;
    }

    /**
     * Endpoint to create a new donation.
     *
     * @param donationType          The type of donation (e.g., EDUCATION_SUPPORT,
     *                              HEALTH_SUPPORT).
     * @param senderAccountNumber   The account number of the sender.
     * @param receiverAccountNumber The account number of the receiver.
     * @param kiloWatts             The amount of kilowatts to donate.
     * @param createdAt             The date and time when the donation was created.
     * @return The created donation as a DonationsDTO.
     */
    @PostMapping("/createDonation")
    public ResponseEntity<Donation> createDonation(
            @RequestParam Donations donationType,
            @RequestParam String senderAccountNumber,
            @RequestParam String receiverAccountNumber,
            @RequestParam Double kiloWatts,
            @RequestParam LocalDateTime createdAt) {
        try {
            Donation newDonations = donationsServices.createDonation(
                    donationType,
                    senderAccountNumber,
                    receiverAccountNumber,
                    kiloWatts,
                    createdAt);
            return ResponseEntity.status(HttpStatus.CREATED).body(newDonations);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }

}
