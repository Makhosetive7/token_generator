package com.example.ElectricityTokenGenerator.services.Tokens;

import com.example.ElectricityTokenGenerator.dto.Tokens.LocalVendorDTO;
import com.example.ElectricityTokenGenerator.entity.Tokens.LocalVendor;
import com.example.ElectricityTokenGenerator.entity.Tokens.Token;
import com.example.ElectricityTokenGenerator.entity.Users.User;
import com.example.ElectricityTokenGenerator.enums.LocalVendors;
import com.example.ElectricityTokenGenerator.mappers.Tokens.LocalVendorMapper;
import com.example.ElectricityTokenGenerator.repository.Tokens.LocalVendorRepository;
import com.example.ElectricityTokenGenerator.repository.Tokens.TokenRepository;
import com.example.ElectricityTokenGenerator.repository.Users.userRepository;

import jakarta.transaction.Transactional;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

@Service
public class LocalVendorServices {

    public static final Double CONVERSION_RATE = 2.5 / 5;
    private final Double MINIMUM_KILOWATTS_CONVERTED = 250.0;

    private final LocalVendorRepository localVendorRepository;
    private final TokenRepository tokenRepository;
    private final userRepository userRepository;
    private final LocalVendorMapper localVendorMapper;

    public LocalVendorServices(
            LocalVendorRepository localVendorRepository,
            TokenRepository tokenRepository,
            userRepository userRepository,
            LocalVendorMapper localVendorMapper) {
        this.localVendorRepository = localVendorRepository;
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
        this.localVendorMapper = localVendorMapper;
    }

    @Transactional
    public LocalVendorDTO purchaseProduct(
            String senderAccountNumber,
            String receiverAccountNumber,
            Double kilowatts,
            LocalVendors vendorType,
            LocalDateTime createdAt) {
        // Fetch the sender (Buyer) Account Number from database
        User sender = userRepository.findByAccountNumber(senderAccountNumber)
                .orElseThrow(() -> new RuntimeException(
                        "User (buyer) not found with account number " + senderAccountNumber));

        // Fetch the receiver (Local Vendor) Account Number from database
        User vendorAccount = userRepository.findByAccountNumber(receiverAccountNumber)
                .orElseThrow(() -> new RuntimeException(
                        "Vendor account not found with account number " + receiverAccountNumber));

        // Validate if the user has enough kilowatts to convert
        if (sender.getKiloWatts() < MINIMUM_KILOWATTS_CONVERTED) {
            throw new IllegalArgumentException(
                    "Insufficient kilowatts to convert. User must have at least 250 kilowatts.");
        }

        // Calculate the converted value based on the purchase amount
        Double convertedValue = kilowatts * CONVERSION_RATE;

        // Update the sender's account after purchase
        sender.setKiloWatts(sender.getKiloWatts() - kilowatts);
        userRepository.save(sender);

        // Update the vendor's account after purchase
        vendorAccount.setKiloWatts(vendorAccount.getKiloWatts() + kilowatts);
        userRepository.save(vendorAccount);

        // Create and save the vendor purchase
        LocalVendor purchaseProduct = new LocalVendor();
        purchaseProduct.setVendorAccountNumber(vendorAccount);
        purchaseProduct.setPurchaseAccountNumber(sender.getAccountNumber());
        purchaseProduct.setVendorTypeEnumerator(vendorType);
        purchaseProduct.setConvertedValue(convertedValue);
        purchaseProduct.setPurchaseAmount(kilowatts);
        purchaseProduct.setCreatedAt(createdAt);

        LocalVendor savedPurchase = localVendorRepository.save(purchaseProduct);

        // Map the LocalVendor entity to LocalVendorDTO
        return localVendorMapper.toDto(savedPurchase);
    }

}