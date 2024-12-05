package com.example.ElectricityTokenGenerator.controllers.TokensController;


import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ElectricityTokenGenerator.entity.Tokens.DonationsEntity;
import com.example.ElectricityTokenGenerator.services.Tokens.DonationsServices;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/tokens/donations")
public class DonationsController {

    private final DonationsServices donationsServices;

    private DonationsController(DonationsServices donationsServices) {
        this.donationsServices = donationsServices;
    }

    @PostMapping("/createDonation")
    public ResponseEntity <DonationsEntity> createDonation(@RequestBody DonationsEntity request) {
        DonationsEntity newDonations = donationsServices.createDonation(
            request.getAccountNumber().getAccountNumber(), 
            request.getAccountNumber().getKiloWatts(),
            request.getAmountPaid(),
            request.getSerialNumber(),
            request.getDonationType(),
            LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(newDonations);
    }


    
}
