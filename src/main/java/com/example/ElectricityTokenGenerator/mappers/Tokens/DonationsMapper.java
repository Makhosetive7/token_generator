package com.example.ElectricityTokenGenerator.mappers.Tokens;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.ElectricityTokenGenerator.dto.Tokens.DonationsDTO;
import com.example.ElectricityTokenGenerator.entity.Tokens.DonationsEntity;
import com.example.ElectricityTokenGenerator.entity.Tokens.TokenEntities;

@Mapper
public interface DonationsMapper {

    DonationsMapper INSTANCE = Mappers.getMapper(DonationsMapper.class);

    // Map DonationsEntity to DonationsDTO
    @Mapping(source = "donatorsAccountNumber", target = "donatorsAccountNumber")
    @Mapping(source = "donationAccountNumber", target = "donationAccountNumber")
    @Mapping(source = "kiloWatts", target = "kiloWatts")
    @Mapping(source = "donationType", target = "donationType")
    @Mapping(source = "createdAt", target = "createdAt")
    DonationsDTO toDto(DonationsEntity donationsEntity);

    // Map DonationsDTO to DonationsEntity
    @Mapping(source = "donatorsAccountNumber", target = "donatorsAccountNumber")
    @Mapping(source = "donationAccountNumber", target = "donationAccountNumber")
    @Mapping(source = "kiloWatts", target = "kiloWatts")
    @Mapping(source = "donationType", target = "donationType")
    @Mapping(source = "createdAt", target = "createdAt")
    DonationsEntity toEntity(DonationsDTO donationsDTO);

    // Custom mapping method for TokenEntities to String
    default String map(TokenEntities tokenEntities) {
        return tokenEntities.getAccountNumber();
    }

    // Custom mapping method for String to TokenEntities
    default TokenEntities map(String accountNumber) {
        TokenEntities tokenEntities = new TokenEntities();
        tokenEntities.setAccountNumber(accountNumber);
        return tokenEntities;
    }
}
