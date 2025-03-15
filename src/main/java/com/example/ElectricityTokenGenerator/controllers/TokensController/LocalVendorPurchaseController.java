package com.example.ElectricityTokenGenerator.controllers.TokensController;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ElectricityTokenGenerator.dto.Tokens.LocalVendorDTO;
import com.example.ElectricityTokenGenerator.enums.LocalVendors;
import com.example.ElectricityTokenGenerator.services.Tokens.LocalVendorServices;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("api/tokens/localVendor")
public class LocalVendorPurchaseController {

    private final LocalVendorServices localVendorServices;

    public LocalVendorPurchaseController(LocalVendorServices localVendorServices) {
        this.localVendorServices = localVendorServices;
    }

    /**
     * Endpoint to purchase a product from a local vendor.
     *
     * @param senderAccountNumber   The account number of the buyer.
     * @param receiverAccountNumber The account number of the vendor.
     * @param kilowatts             The amount of kilowatts to purchase.
     * @param vendorType            The type of vendor (e.g., GROCERY_STORE).
     * @param createdAt             The date and time of the purchase.
     * @return The created purchase as a LocalVendorDTO.
     */
    @PostMapping("/purchase")
    public ResponseEntity<LocalVendorDTO> purchaseProduct(
            @RequestParam String senderAccountNumber,
            @RequestParam String receiverAccountNumber,
            @RequestParam Double kilowatts,
            @RequestParam LocalVendors vendorType,
            @RequestParam LocalDateTime createdAt) {

        try {
            // Call the service to create the purchase
            LocalVendorDTO localVendorDTO = localVendorServices.purchaseProduct(
                    senderAccountNumber,
                    receiverAccountNumber,
                    kilowatts,
                    vendorType,
                    createdAt);

            return ResponseEntity.status(HttpStatus.CREATED).body(localVendorDTO);
        } catch (RuntimeException e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

}
