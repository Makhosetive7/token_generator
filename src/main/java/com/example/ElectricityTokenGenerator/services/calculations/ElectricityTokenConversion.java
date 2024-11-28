package com.example.ElectricityTokenGenerator.services.calculations;

import org.springframework.stereotype.Service;

@Service
public class ElectricityTokenConversion {

    // $1 is equivalent to 1.4 kiloWatts units
    private static final Double conversion_rate = 1.4;

    // method to convert amountPaid into kiloWatts
    public Double convertAmountPaidToKilowatts(Double amountPaid) {
        return amountPaid * conversion_rate;

    }

}