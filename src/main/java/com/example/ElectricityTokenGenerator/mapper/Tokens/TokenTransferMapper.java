package com.example.ElectricityTokenGenerator.mapper.Tokens;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.ElectricityTokenGenerator.dto.Tokens.TokenTransferDTO;
import com.example.ElectricityTokenGenerator.entity.Tokens.TokenTransferEntity;

@Mapper
public interface TokenTransferMapper {

    TokenTransferMapper INSTANCE = Mappers.getMapper(TokenTransferMapper.class);
    //Map Entities to DTO
    @Mapping(source = "senderAccountNumber", target = "senderAccountNumber")
    @Mapping(source = "receiverAccountNumber", target = "receiverAccountNumber")
    @Mapping(source = "kiloWattsTransferred", target = "kiloWattsTransferred")
    @Mapping(source = "createdAt", target = "createdAt")
    TokenTransferDTO toDto(TokenTransferEntity tokenTransferEntity);

    //Map DTO to Entities
    @Mapping(source = "senderAccountNumber", target = "senderAccountNumber")
    @Mapping(source = "receiverAccountNumber", target = "receiverAccountNumber")
    @Mapping(source = "kiloWattsTransferred", target = "kiloWattsTransferred")
    @Mapping(source = "createdAt", target = "createdAt")
    TokenTransferEntity toEntity(TokenTransferDTO tokenTransferDTO);




}
