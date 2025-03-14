package com.example.ElectricityTokenGenerator.mappers.Tokens;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.ElectricityTokenGenerator.dto.Tokens.TokenDTO;
import com.example.ElectricityTokenGenerator.entity.Tokens.Token;

@Mapper
public interface TokensMapper {

    TokensMapper INSTANCE = Mappers.getMapper(TokensMapper.class);

    // Map TokenDTO to Token entity
    @Mapping(source = "tokenBuyer", target = "tokenBuyer.accountNumber") 
    @Mapping(source = "tokenCode", target = "tokenCode") 
    @Mapping(source = "amount", target = "amount")
    @Mapping(source = "kiloWatts", target = "kiloWatts") 
    @Mapping(source = "purchaseDate", target = "purchaseDate")
    @Mapping(source = "expirationDate", target = "expirationDate") 
    @Mapping(source = "status", target = "status") 
    @Mapping(target = "id", ignore = true) 
    Token toEntity(TokenDTO tokenDTO);

    // Map Token entity to TokenDTO
    @Mapping(source = "tokenBuyer.accountNumber", target = "tokenBuyer") 
    @Mapping(source = "tokenCode", target = "tokenCode") 
    @Mapping(source = "amount", target = "amount")
    @Mapping(source = "kiloWatts", target = "kiloWatts") 
    @Mapping(source = "purchaseDate", target = "purchaseDate")
    @Mapping(source = "expirationDate", target = "expirationDate") 
    @Mapping(source = "status", target = "status") 
    TokenDTO toDto(Token token);
}