package com.example.ElectricityTokenGenerator.mapper.Tokens;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.ElectricityTokenGenerator.dto.Tokens.DonationsDTO;
import com.example.ElectricityTokenGenerator.entity.Tokens.DonationsEntity;

@Mapper
public interface DonationsMapper {

    // Get the instance of the mapper
    DonationsMapper INSTANCE = Mappers.getMapper(DonationsMapper.class); 

    // Map DonationsEntity to DonationsDTO
    @Mapping(source = "donationAccountNumber", target = "donationAccountNumber")
    @Mapping(source = "donatorsAccountNumber", target = "donatorsAccountNumber")
    @Mapping(source = "amountDonated", target = "amountDonated")
    @Mapping(source = "kiloWatts", target = "kiloWatts")
    @Mapping(source = "donationType", target = "donationType")
    @Mapping(source = "createdAt", target = "createdAt")
    DonationsDTO toDto(DonationsEntity donationsEntity);

    // Map DonationsDTO to DonationsEntity
    @Mapping(source = "donationAccountNumber", target = "donationAccountNumber")
    @Mapping(source = "donatorsAccountNumber", target = "donatorsAccountNumber")
    @Mapping(source = "amountDonated", target = "amountDonated")
    @Mapping(source = "kiloWatts", target = "kiloWatts")
    @Mapping(source = "donationType", target = "donationType")
    @Mapping(source = "createdAt", target = "createdAt")
    DonationsEntity toEntity(DonationsDTO donationsDTO);
}
