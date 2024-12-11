package com.example.ElectricityTokenGenerator.controllers.TokensController;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ElectricityTokenGenerator.dto.Tokens.LocalVendorDTO;
import com.example.ElectricityTokenGenerator.entity.Tokens.LocalVendorEntity;
import com.example.ElectricityTokenGenerator.services.Tokens.LocalVendorServices;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/tokens/localVendor")
public class LocalVendorPurchaseController {
    
    private final LocalVendorServices localVendorServices;

    public LocalVendorPurchaseController(LocalVendorServices localVendorServices) {
        this.localVendorServices = localVendorServices;
    }

    @PostMapping("/purchase")
    public ResponseEntity<LocalVendorEntity> purchaseProduct(@RequestBody LocalVendorDTO request) {
        LocalVendorEntity newPurchase = localVendorServices.purchaseProduct(
            request.getVendorAccountNumber(),
            request.getPurchaseAccountNumber(),
            request.getVendorTypeEnumerator(),
            request.getConvertedValue(),
            request.getKiloWatts(),
            request.getPurchaseAmount(),
            LocalDateTime.now()
        );
    
        return ResponseEntity.status(HttpStatus.OK).body(newPurchase);
    }
    

}
