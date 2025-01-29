package com.example.ElectricityTokenGenerator.mappers.Tokens;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.ElectricityTokenGenerator.dto.Tokens.TokensDTO;
import com.example.ElectricityTokenGenerator.entity.Tokens.TokenEntities;

@Mapper
public interface TokensMapper {

    TokensMapper INSTANCE = Mappers.getMapper(TokensMapper.class);

    // Map TokenEntity to TokenDTO
    @Mapping(source = "accountNumber", target = "accountNumber")
    @Mapping(source = "amountPaid", target = "amountPaid")
    @Mapping(source = "tokenGenerated", target = "tokenGenerated")
    @Mapping(source = "serialNumber", target = "serialNumber")
    @Mapping(source = "createdAt", target = "createdAt")
    TokensDTO toDto(TokenEntities tokenEntities);

    // Map TokenDTO to TokenEntity
    @Mapping(source = "accountNumber", target = "accountNumber")
    @Mapping(source = "amountPaid", target = "amountPaid")
    @Mapping(target = "tokenGenerated", ignore = true)
    @Mapping(target = "serialNumber", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    TokenEntities toEntity(TokensDTO tokensDTO);

    
}
