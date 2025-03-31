package com.example.ElectricityTokenGenerator.mappers.Tokens;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.ElectricityTokenGenerator.dto.Tokens.TokenTransferDTO;
import com.example.ElectricityTokenGenerator.entity.Tokens.TokenTransfer;

@Mapper(componentModel = "spring")
public interface TokenTransferMapper {

    TokenTransferMapper INSTANCE = Mappers.getMapper(TokenTransferMapper.class);

    // Map TokenTransfer entity to TokenTransferDTO
    @Mapping(source = "sender.accountNumber", target = "senderAccountNumber")
    @Mapping(source = "receiver.accountNumber", target = "receiverAccountNumber")
    @Mapping(source = "kiloWatts", target = "kiloWatts")
    @Mapping(source = "createdAt", target = "createdAt")
    TokenTransferDTO toDto(TokenTransfer tokenTransfer);

    // Map TokenTransferDTO to TokenTransfer entity
    @Mapping(source = "senderAccountNumber", target = "sender.accountNumber")
    @Mapping(source = "receiverAccountNumber", target = "receiver.accountNumber")
    @Mapping(source = "kiloWatts", target = "kiloWatts")
    @Mapping(source = "createdAt", target = "createdAt")
    TokenTransfer toEntity(TokenTransferDTO tokenTransferDTO);
}