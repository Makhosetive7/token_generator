package com.example.ElectricityTokenGenerator.mappers.Tokens;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.ElectricityTokenGenerator.dto.Tokens.TokenTransferDTO;
import com.example.ElectricityTokenGenerator.entity.Tokens.TokenTransferEntity;
import com.example.ElectricityTokenGenerator.entity.Tokens.TokenEntities;

@Mapper
public interface TokenTransferMapper {

    TokenTransferMapper INSTANCE =  Mappers.getMapper(TokenTransferMapper.class);

    // Map TokenTransferEntity to TokenTransferDTO
    @Mapping(source = "senderAccountNumber", target = "senderAccountNumber")
    @Mapping(source = "receiverAccountNumber", target = "receiverAccountNumber")
    @Mapping(source = "kiloWatts", target = "kiloWatts")
    @Mapping(source = "createdAt", target = "createdAt")
    TokenTransferDTO toDto(TokenTransferEntity tokenTransferEntity);

    // Map TokenTransferDTO to TokenTransferEntity
    @Mapping(source = "senderAccountNumber", target = "senderAccountNumber")
    @Mapping(source = "receiverAccountNumber", target = "receiverAccountNumber")
    @Mapping(source = "kiloWatts", target = "kiloWatts")
    @Mapping(source = "createdAt", target = "createdAt")
    TokenTransferEntity toEntity(TokenTransferDTO tokenTransferDTO);

    // Custom mapping method for TokenEntities to String
    default String map(TokenEntities tokenEntities) {
        return tokenEntities.getAccountNumber();  // Assuming TokenEntities has a getAccountNumber method
    }

    // Custom mapping method for String to TokenEntities
    default TokenEntities map(String accountNumber) {
        TokenEntities tokenEntities = new TokenEntities();
        tokenEntities.setAccountNumber(accountNumber);  // Assuming TokenEntities has a setAccountNumber method
        return tokenEntities;
    }
}
