package com.example.ElectricityTokenGenerator.enums;

public enum LocalVendors {
    CHOPPIES("Choppies Supermarket"),
    ZAPALALA("Zapalala Wholesale"),
    EDGARS("Edgars Stores"),
    DOVES("Doves Funeral Services");

    private final String displayName;

    LocalVendors(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
