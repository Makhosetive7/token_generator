package com.example.ElectricityTokenGenerator.enums;

public enum DonationsEnumerator {
    EDUCATION_SUPPORT("School support"),
    FOOD_PROGRAMS("Food programs"),
    HEALTH_PROGRAMS("Health programs");

    private final String displayName;

    DonationsEnumerator(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
