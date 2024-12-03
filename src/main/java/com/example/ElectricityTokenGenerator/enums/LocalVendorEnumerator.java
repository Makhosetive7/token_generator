package com.example.ElectricityTokenGenerator.enums;

public enum LocalVendorEnumerator {
    CHOPPIES("Choppies Supermarket"),
    ZAPALALA("Zapalala Wholesale"),
    EDGARS("Edgars Stores"),
    DOVES("Doves Funeral Services");

    private final String displayName;

    LocalVendorEnumerator(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
