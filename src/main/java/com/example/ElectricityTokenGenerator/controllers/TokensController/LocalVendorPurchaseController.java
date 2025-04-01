package com.example.ElectricityTokenGenerator.controllers.TokensController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ElectricityTokenGenerator.dto.Tokens.LocalVendorDTO;
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
    public ResponseEntity<?> purchaseProduct(@RequestBody LocalVendorDTO localVendorDTO) {
        try {
            LocalVendorDTO result = localVendorServices.purchaseProduct(
                    localVendorDTO.getVendorAccountNumber(),
                    localVendorDTO.getPurchaseAccountNumber(),
                    localVendorDTO.getPurchaseAmount(),
                    localVendorDTO.getVendorTypeEnumerator(),
                    localVendorDTO.getCreatedAt());
            
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body("An error occurred: " + e.getMessage());
        }
    }
}
