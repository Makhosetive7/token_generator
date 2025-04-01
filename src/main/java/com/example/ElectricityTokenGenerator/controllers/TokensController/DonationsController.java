package com.example.ElectricityTokenGenerator.controllers.TokensController;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ElectricityTokenGenerator.dto.Tokens.DonationsDTO;
import com.example.ElectricityTokenGenerator.entity.Tokens.Donation;
import com.example.ElectricityTokenGenerator.services.Tokens.DonationsServices;
import org.springframework.web.bind.annotation.RequestMapping;


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
     * @param donationsDTO Contains:
     *                     - donationType (e.g., EDUCATION_SUPPORT, HEALTH_SUPPORT)
     *                     - senderAccountNumber
     *                     - receiverAccountNumber
     *                     - kiloWatts
     *                     - createdAt
     * @return The created donation as a Donation entity.
     */
    @PostMapping("/createDonation")
    public ResponseEntity<?> createDonation(@RequestBody DonationsDTO donationsDTO) {
        try {
            Donation newDonation = donationsServices.createDonation(
                    donationsDTO.getDonationType(),
                    donationsDTO.getSenderAccountNumber(),
                    donationsDTO.getReceiverAccountNumber(),
                    donationsDTO.getKiloWatts(),
                    donationsDTO.getCreatedAt());
            return ResponseEntity.status(HttpStatus.CREATED).body(newDonation);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body("An error occurred: " + e.getMessage());
        }
    }
}