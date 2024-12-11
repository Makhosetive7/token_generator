package com.example.ElectricityTokenGenerator.services.Tokens;

import com.example.ElectricityTokenGenerator.entity.TokensEntity;
import com.example.ElectricityTokenGenerator.entity.Tokens.LocalVendorEntity;
import com.example.ElectricityTokenGenerator.enums.LocalVendorEnumerator;
import com.example.ElectricityTokenGenerator.repository.UserRepository;
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
    private final UserRepository userRepository;


    public LocalVendorServices(LocalVendorRepository localVendorRepository, tokensRepository tokensRepository, UserRepository userRepository) {
        this.localVendorRepository = localVendorRepository;
        this.tokensRepository = tokensRepository;
        this.userRepository = userRepository;
    }

    @Transactional
public LocalVendorEntity purchaseProduct(Long vendorAccountNumber,Long purchaseAccountNumber ,LocalVendorEnumerator vendorTypeEnumerator, Double convertedValue,Double kilowatts,Double purchaseAmount, LocalDateTime createdAt) {
    // Fetch the vendor Account Number
    Optional<TokensEntity> vendorAccountOptional = tokensRepository.findByAccountNumber(vendorAccountNumber);
    if (vendorAccountOptional.isEmpty()) {
        throw new IllegalArgumentException("User account number not found.");
    }

    //Fetch buyer account number
    Optional<TokensEntity> purchaseAccountOptional = tokensRepository.findByAccountNumber(purchaseAccountNumber);
    if (purchaseAccountOptional.isEmpty()) {
        throw new IllegalArgumentException("Purchase account number not found.");
    }

    TokensEntity vendorAccount = vendorAccountOptional.get();
    TokensEntity purchaseAccount = purchaseAccountOptional.get();

     // Validate if the user has enough kilowatts
    if(vendorAccount.getKiloWatts() < MINIMUM_KILOWATTS_CONVERTED){
        throw new IllegalArgumentException("Insufficient KiloWatts to convert, user account required to have more than 250.0 KiloWatts");
    }

    if (vendorAccount.getKiloWatts() < kilowatts) {
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
    vendorAccount.setKiloWatts(vendorAccount.getKiloWatts() - kilowatts);
    tokensRepository.save(vendorAccount);

    // Create and save the vendor purchase
    LocalVendorEntity purchaseProduct = new LocalVendorEntity();
        purchaseProduct.setVendorAccountNumber(vendorAccount);
        purchaseProduct.setPurchaseAccountNumber(purchaseAccount);
        purchaseProduct.setVendorTypeEnumerator(vendorTypeEnumerator);
        purchaseProduct.setConvertedValue(convertedValue);
        purchaseProduct.setPurchaseAmount(purchaseAmount);
        purchaseProduct.setCreatedAt(createdAt);

    return localVendorRepository.save(purchaseProduct);
}

}