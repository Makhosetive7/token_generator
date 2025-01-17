package com.example.ElectricityTokenGenerator.mapper.Tokens;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.ElectricityTokenGenerator.dto.Tokens.TokensDTO;
import com.example.ElectricityTokenGenerator.entity.Tokens.TokenEntities;

@Mapper
public interface TokenMapper {

    TokenMapper INSTANCE = Mappers.getMapper(TokenMapper.class);

    //Map Entities to DTO
    @Mapping(source = "accountNumber", target = "accountNumber")
    @Mapping(source = "amountPaid", target = "amountPaid")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "tokenGenerated", target = "tokenGenerated")
    @Mapping(source = "serialNumber", target = "serialNumber")
    TokensDTO toDto(TokenEntities tokenEntities);

    // Map DTO to Entity
    @Mapping(source = "accountNumber", target = "accountNumber")
    @Mapping(source = "amountPaid", target = "amountPaid")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "tokenGenerated", target = "tokenGenerated")
    @Mapping(source = "serialNumber", target = "serialNumber")
    TokenEntities toEntity(TokensDTO tokensDTO);


}
