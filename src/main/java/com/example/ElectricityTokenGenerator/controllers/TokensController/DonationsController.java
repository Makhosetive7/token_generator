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

    @PostMapping("/createDonation")
    public ResponseEntity<Donation> createDonation(@RequestBody DonationsDTO request) {
        Donation newDonations = donationsServices.createDonation(
                request.getDonationType(),
                request.getDonatorsAccountNumber(),
                request.getDonationAccountNumber(),
                request.getKiloWatts(),
                LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CREATED).body(newDonations);
    }

}
