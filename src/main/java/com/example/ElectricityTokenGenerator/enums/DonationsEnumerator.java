package com.example.ElectricityTokenGenerator.enums;

public enum DonationsEnumerator {
    EDUCATION_SUPPORT("0076623901"),
    HEALTH_SUPPORT("0087632102"),
    WATER_PROJECT("0098765401"),
    ELECTRIFICATION("0078632903");

    private final String requiredAccountNumber;

    DonationsEnumerator(String requiredAccountNumber) {
        this.requiredAccountNumber = requiredAccountNumber;
    }

    public String getRequiredAccountNumber() {
        return requiredAccountNumber;
    }
}
