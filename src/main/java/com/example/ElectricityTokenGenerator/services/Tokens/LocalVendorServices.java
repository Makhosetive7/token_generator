package com.example.ElectricityTokenGenerator.services.Tokens;

import com.example.ElectricityTokenGenerator.entity.Tokens.LocalVendorEntity;
import com.example.ElectricityTokenGenerator.entity.Tokens.TokenEntities;
import com.example.ElectricityTokenGenerator.enums.LocalVendorEnumerator;
import com.example.ElectricityTokenGenerator.repository.Tokens.LocalVendorRepository;
import com.example.ElectricityTokenGenerator.repository.Tokens.TokenRepository;
import com.example.ElectricityTokenGenerator.repository.Users.userRepository;

import jakarta.transaction.Transactional;

import java.time.LocalDateTime;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class LocalVendorServices {

    public static final Double CONVERSION_RATE = 2.5 / 5;

    private final LocalVendorRepository localVendorRepository;
    private final TokenRepository tokenRepository;
    private final userRepository userRepository;

    public LocalVendorServices(LocalVendorRepository localVendorRepository, TokenRepository tokenRepository,
            userRepository userRepository) {
        this.localVendorRepository = localVendorRepository;
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public LocalVendorEntity purchaseProduct(String vendorAccountNumber, String purchaseAccountNumber,
            LocalVendorEnumerator vendorTypeEnumerator, Double convertedValue, Double kilowatts, Double purchaseAmount,
            LocalDateTime createdAt) {
        // Fetch the vendor Account Number
        Optional<TokenEntities> vendorAccountOptional = tokenRepository.findByAccountNumber(vendorAccountNumber);
        if (vendorAccountOptional.isEmpty()) {
            throw new IllegalArgumentException("User account number not found.");
        }

        // Fetch buyer account number
        Optional<TokenEntities> purchaseAccountOptional = tokenRepository.findByAccountNumber(purchaseAccountNumber);
        if (purchaseAccountOptional.isEmpty()) {
            throw new IllegalArgumentException("Purchase account number not found.");
        }

        TokenEntities vendorAccount = vendorAccountOptional.get();
        TokenEntities purchaseAccount = purchaseAccountOptional.get();
        
       //calculate amount of kilowatts to be converted based  on purchase amount
        Double kilowattsToConvert = purchaseAmount / CONVERSION_RATE;

        // Validate if the user has enough kilowatts to convert
        if (kilowattsToConvert > purchaseAccount.getKiloWatts()) {
            throw new IllegalArgumentException("Insufficient kilowatts to convert.");
        }

        // Deduct the kilowatts
        purchaseAccount.setKiloWatts(purchaseAccount.getKiloWatts() - kilowattsToConvert);
        tokenRepository.save(purchaseAccount);

        // Add the kilowatts to the vendor account
        vendorAccount.setKiloWatts(vendorAccount.getKiloWatts() + kilowattsToConvert);
        tokenRepository.save(vendorAccount);


        //update users kilowatts and save the updated user
        userRepository.findByAccountNumber(purchaseAccountNumber).ifPresentOrElse(user -> {
            double currentKiloWatts = user.getKiloWatts() != null ? user.getKiloWatts() : 0.0;
            double newKiloWatts = currentKiloWatts - kilowattsToConvert;
            user.setKiloWatts(newKiloWatts);
            userRepository.save(user);
            System.out.println("User updated: " + user);
        }, () -> {
            System.out.println("User not found with accountNumber: " + purchaseAccountNumber);
        });

        //update vendor kilowatts and save the updated user
        userRepository.findByAccountNumber(vendorAccountNumber).ifPresentOrElse(user -> {
            double currentKiloWatts = user.getKiloWatts() != null ? user.getKiloWatts() : 0.0;
            double newKiloWatts = currentKiloWatts + kilowattsToConvert;
            user.setKiloWatts(newKiloWatts);
            userRepository.save(user);
            System.out.println("User updated: " + user);
        }, () -> {
            System.out.println("User not found with accountNumber: " + vendorAccountNumber);
        });

        // Create and save the vendor purchase
        LocalVendorEntity purchaseProduct = new LocalVendorEntity();
        purchaseProduct.setVendorAccountNumber(vendorAccount);
        purchaseProduct.setPurchaseAccountNumber(purchaseAccount);
        purchaseProduct.setVendorTypeEnumerator(vendorTypeEnumerator);
        purchaseProduct.setConvertedValue(kilowatts);
        purchaseProduct.setPurchaseAmount(purchaseAmount);
        purchaseProduct.setCreatedAt(createdAt);

        return localVendorRepository.save(purchaseProduct);
    }

}