package com.example.ElectricityTokenGenerator.entity.Tokens;

import com.example.ElectricityTokenGenerator.entity.Users.User;
import com.example.ElectricityTokenGenerator.enums.LocalVendors;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "local_vendor")
public class LocalVendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vendor_account_number", nullable = false)
    private User vendorAccountNumber;

    @ManyToOne
    @JoinColumn(name = "purchase_account_number", nullable = false)
    private User purchaseAccountNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "vendor_type_enumerator", nullable = false)
    private LocalVendors vendorTypeEnumerator;

    @Column(name = "converted_value", nullable = false)
    private Double convertedValue;

    @Column(name = "purchase_amount", nullable = false)
    private Double purchaseAmount;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}