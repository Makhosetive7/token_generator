package com.example.ElectricityTokenGenerator.mappers.Tokens;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.ElectricityTokenGenerator.dto.Tokens.DonationsDTO;
import com.example.ElectricityTokenGenerator.entity.Tokens.Donation;

@Mapper(componentModel = "spring")
public interface DonationsMapper {

    DonationsMapper INSTANCE = Mappers.getMapper(DonationsMapper.class);

     // Map DonationsDTO to Donation entity
     @Mapping(source = "senderAccountNumber", target = "senderAccountNumber.accountNumber") 
     @Mapping(source = "receiverAccountNumber", target = "receiverAccountNumber.accountNumber") 
     @Mapping(source = "KiloWatts", target = "kiloWatts.kiloWatts")
     @Mapping(source = "amount", target = "amount")
     @Mapping(source = "donationType", target = "DonationType")
     @Mapping(target = "createdAt", ignore = true) 
     Donation toEntity(DonationsDTO donationsDTO);
 
     // Map Donation entity to DonationsDTO
     @Mapping(source = "sender.accountNumber", target = "senderAccountNumber")
     @Mapping(source = "receiver.accountNumber", target = "receiverAccountNumber")
     @Mapping(source = "kiloWatts.kiloWatts", target = "kiloWatts")
     @Mapping(source = "donationType", target = "donationType")
     @Mapping(source = "amount", target = "amount")
     @Mapping(target = "createdAt", ignore = true)  
     DonationsDTO toDto(Donation donation);

}
