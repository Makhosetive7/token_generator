package com.example.ElectricityTokenGenerator.mappers.Tokens;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public class DonationsMapper {

    DonationsMapper INSTANCE = Mappers.getMapper(DonationsMapper.class);

    // Add donationEntities to DonationDtos
    @Mapping(source = "donationEntities", target = "donationDtos")






    
}
