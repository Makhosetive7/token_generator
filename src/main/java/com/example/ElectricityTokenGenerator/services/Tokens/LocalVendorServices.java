package com.example.ElectricityTokenGenerator.services.Tokens;

import com.example.ElectricityTokenGenerator.entity.TokensEntity;
import com.example.ElectricityTokenGenerator.entity.Tokens.LocalVendorEntity;
import com.example.ElectricityTokenGenerator.enums.LocalVendorEnumerator;
import com.example.ElectricityTokenGenerator.repository.tokensRepository;
import com.example.ElectricityTokenGenerator.repository.Tokens.LocalVendorRepository;

import jakarta.transaction.Transactional;

import java.time.LocalDateTime;

import java.util.Optional;
public class LocalVendorServices {
    
    private final LocalVendorRepository localVendorRepository;
    private final tokensRepository tokensRepository;


    public LocalVendorServices(LocalVendorRepository localVendorRepository, tokensRepository tokensRepository) {
        this.localVendorRepository = localVendorRepository;
        this.tokensRepository = tokensRepository;
    }

    @Transactional
    public LocalVendorEntity purchaseProduct(LocalVendorEnumerator vendorType, Double convertedValue, Long accountNumber, Double kilowatts, LocalDateTime createdAt) {

        Optional<LocalVendorEntity> vendorAccountOptional = localVendorRepository.findById(accountNumber); 
        if (vendorAccountOptional.isEmpty()) 
        { throw new IllegalArgumentException("Vendor account not found."); }


        Optional<TokensEntity> userAccountOptional = tokensRepository.findById(accountNumber);
         if (userAccountOptional.isEmpty()) 
         { throw new IllegalArgumentException("User account not found."); }

         TokensEntity userAccount = userAccountOptional.get();
         LocalVendorEntity vendorAccount = vendorAccountOptional.get();


         if (userAccount.getKiloWatts() < convertedValue) {
            throw new IllegalArgumentException("Insufficient balance to purchase product.");
        }


         userAccount.setKiloWatts(userAccount.getKiloWatts() - convertedValue);
         tokensRepository.save(userAccount);

         LocalVendorEntity purchaseProduct = new LocalVendorEntity();
        vendorAccount.setAccountNumber(userAccount);
        vendorAccount.setVendorTypeEnumerator(vendorType);
        vendorAccount.setConvertedValue(convertedValue);
        vendorAccount.setCreatedAt(createdAt);


         return localVendorRepository.save(purchaseProduct);

}
}