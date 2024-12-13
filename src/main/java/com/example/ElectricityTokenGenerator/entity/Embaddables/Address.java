package com.example.ElectricityTokenGenerator.entity.Embaddables;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {
    private String city;
    private String province;
    private String suburb;
    
}
