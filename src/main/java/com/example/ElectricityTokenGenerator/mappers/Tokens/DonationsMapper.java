package com.example.ElectricityTokenGenerator.mappers.Tokens;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.ElectricityTokenGenerator.dto.Tokens.DonationsDTO;
import com.example.ElectricityTokenGenerator.entity.Tokens.Donation;

@Mapper(componentModel = "spring")
public interface DonationsMapper {

    DonationsMapper INSTANCE = Mappers.getMapper(DonationsMapper.class);

    // Map Donation entity to DonationsDTO
    @Mapping(source = "donationType", target = "donationType")
    @Mapping(source = "sender.accountNumber", target = "senderAccountNumber")
    @Mapping(source = "receiver.accountNumber", target = "receiverAccountNumber")
    @Mapping(source = "kiloWatts", target = "kiloWatts")
    @Mapping(source = "convertedValue", target = "convertedValue")
    @Mapping(source = "createdAt", target = "createdAt")
    DonationsDTO toDto(Donation donation);

    // Map DonationsDTO to Donation entity
    @Mapping(source = "donationType", target = "donationType")
    @Mapping(source = "senderAccountNumber", target = "sender.accountNumber")
    @Mapping(source = "receiverAccountNumber", target = "receiver.accountNumber")
    @Mapping(source = "kiloWatts", target = "kiloWatts")
    @Mapping(source = "convertedValue", target = "convertedValue")
    @Mapping(source = "createdAt", target = "createdAt")
    Donation toEntity(DonationsDTO donationsDTO);
}