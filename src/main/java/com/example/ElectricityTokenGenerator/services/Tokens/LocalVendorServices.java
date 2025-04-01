package com.example.ElectricityTokenGenerator.services.Tokens;

import com.example.ElectricityTokenGenerator.dto.Tokens.LocalVendorDTO;
import com.example.ElectricityTokenGenerator.entity.Tokens.LocalVendor;
import com.example.ElectricityTokenGenerator.entity.Users.User;
import com.example.ElectricityTokenGenerator.enums.LocalVendors;
import com.example.ElectricityTokenGenerator.mappers.Tokens.LocalVendorMapper;
import com.example.ElectricityTokenGenerator.repository.Tokens.LocalVendorRepository;
import com.example.ElectricityTokenGenerator.repository.Users.userRepository;

import jakarta.transaction.Transactional;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

@Service
public class LocalVendorServices {

    public static final Double CONVERSION_RATE = 0.5; 

    private final LocalVendorRepository localVendorRepository;
    private final userRepository userRepository;
    private final LocalVendorMapper localVendorMapper;

    public LocalVendorServices(
            LocalVendorRepository localVendorRepository,
            userRepository userRepository,
            LocalVendorMapper localVendorMapper) {
        this.localVendorRepository = localVendorRepository;
        this.userRepository = userRepository;
        this.localVendorMapper = localVendorMapper;
    }

    @Transactional
    public LocalVendorDTO purchaseProduct(
            String vendorAccountNumber,
            String purchaseAccountNumber,
            Double purchaseAmount,
            LocalVendors vendorType,
            LocalDateTime createdAt) {
        
        // Validate input parameters
        if (vendorAccountNumber == null || purchaseAccountNumber == null) {
            throw new IllegalArgumentException("Account numbers must be provided");
        }
        if (purchaseAmount == null || purchaseAmount <= 0) {
            throw new IllegalArgumentException("Purchase amount must be positive");
        }
        if (vendorType == null) {
            throw new IllegalArgumentException("Vendor type must be specified");
        }
        
        // Fetch accounts from database
        User buyer = userRepository.findByAccountNumber(purchaseAccountNumber)
                .orElseThrow(() -> new RuntimeException(
                        "Buyer account not found: " + purchaseAccountNumber));

        User vendor = userRepository.findByAccountNumber(vendorAccountNumber)
                .orElseThrow(() -> new RuntimeException(
                        "Vendor account not found: " + vendorAccountNumber));

        // Validate buyer's balance
        if (buyer.getKiloWatts() < purchaseAmount) {
            throw new IllegalArgumentException(
                    String.format("Insufficient balance. Available: %.2f, Required: %.2f", 
                            buyer.getKiloWatts(), purchaseAmount));
        }

        // Calculate converted value
        Double convertedValue = purchaseAmount * CONVERSION_RATE;

        // Update balances
        buyer.setKiloWatts(buyer.getKiloWatts() - purchaseAmount);
        vendor.setKiloWatts(vendor.getKiloWatts() + purchaseAmount);
        userRepository.save(buyer);
        userRepository.save(vendor);

        // Create and save transaction
        LocalVendor transaction = LocalVendor.builder()
                .vendorAccountNumber(vendor)
                .purchaseAccountNumber(buyer)
                .vendorTypeEnumerator(vendorType)
                .convertedValue(convertedValue)
                .purchaseAmount(purchaseAmount)
                .createdAt(createdAt)
                .build();

        LocalVendor savedTransaction = localVendorRepository.save(transaction);

        return localVendorMapper.toDto(savedTransaction);
    }
}