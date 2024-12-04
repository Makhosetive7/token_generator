package com.example.ElectricityTokenGenerator.services.Tokens;

import com.example.ElectricityTokenGenerator.entity.TokensEntity;
import com.example.ElectricityTokenGenerator.entity.Tokens.LocalVendorEntity;
import com.example.ElectricityTokenGenerator.enums.LocalVendorEnumerator;
import com.example.ElectricityTokenGenerator.repository.tokensRepository;
import com.example.ElectricityTokenGenerator.repository.Tokens.LocalVendorRepository;

import jakarta.transaction.Transactional;

import java.time.LocalDateTime;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class LocalVendorServices {

    public static final Double CONVERSION_RATE = 2.5 / 5;
    private static final Double MINIMUM_KILOWATTS_CONVERTED = 250.0;
    private static final Double MINIMUM_PURCHASE_AMOUNT = 10.0;
    
    private final LocalVendorRepository localVendorRepository;
    private final tokensRepository tokensRepository;


    public LocalVendorServices(LocalVendorRepository localVendorRepository, tokensRepository tokensRepository) {
        this.localVendorRepository = localVendorRepository;
        this.tokensRepository = tokensRepository;
    }

    @Transactional
public LocalVendorEntity purchaseProduct(LocalVendorEnumerator vendorType, Double convertedValue, Long accountNumber, Double kilowatts,Double purchaseAmount, LocalDateTime createdAt) {
    // Fetch the user account from TokensEntity
    Optional<TokensEntity> userAccountOptional = tokensRepository.findById(accountNumber);
    if (userAccountOptional.isEmpty()) {
        throw new IllegalArgumentException("User account not found.");
    }

    TokensEntity userAccount = userAccountOptional.get();

     // Validate if the user has enough kilowatts
    if(userAccount.getKiloWatts() < MINIMUM_KILOWATTS_CONVERTED){
        throw new IllegalArgumentException("Insufficient KiloWatts to convert, user account required to have more than 250.0 KiloWatts");
    }

    if (userAccount.getKiloWatts() < kilowatts) {
        throw new IllegalArgumentException("Insufficient kilowatts available.");
    }

    // Convert kilowatts to dollars
     convertedValue = convertedValue * CONVERSION_RATE;

    // Validate converted value
    if (convertedValue <= 0) {
        throw new IllegalArgumentException("Converted value must be greater than 0.0");
    }
    // Validate purchase amount
    if (purchaseAmount <= 0) {
        throw new IllegalArgumentException("Purchase amount must be greater than 0.0");
    }

    // Validate if converted value is at least $10.0
    if (convertedValue < MINIMUM_PURCHASE_AMOUNT) {
        throw new IllegalArgumentException("Converted value must be at least $10.0");
    }

    // Deduct the kilowatts
    userAccount.setKiloWatts(userAccount.getKiloWatts() - kilowatts);
    tokensRepository.save(userAccount);

    // Create and save the vendor purchase
    LocalVendorEntity purchaseProduct = LocalVendorEntity.builder()
        .accountNumber(userAccount)
        .vendorTypeEnumerator(vendorType)
        .convertedValue(convertedValue)
        .createdAt(createdAt)
        .purchaseAmount(purchaseAmount)
        .build();

    return localVendorRepository.save(purchaseProduct);
}

}