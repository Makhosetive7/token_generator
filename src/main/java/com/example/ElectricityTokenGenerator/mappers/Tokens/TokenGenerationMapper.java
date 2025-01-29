package com.example.ElectricityTokenGenerator.mappers.Tokens;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.ElectricityTokenGenerator.dto.Tokens.TokensGenerationDTO;
import com.example.ElectricityTokenGenerator.entity.Tokens.TokenGeneratorEntity;

@Mapper
public interface TokenGenerationMapper {

    TokenGenerationMapper INSTANCE = Mappers.getMapper(TokenGenerationMapper.class);

    // Map TokenGenerationEntity to TokenGenerationDTO
    @Mapping(source = "accountNumber", target = "accountNumber")
    @Mapping(source = "amountPaid", target = "amountPaid")
    @Mapping(source = "tokenGenerated", target = "tokenGenerated")
    @Mapping(source = "serialNumber", target = "serialNumber")
    @Mapping(source = "kiloWatts", target = "kiloWatts")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "expiredAt", target = "expiredAt")
    TokensGenerationDTO toDto(TokenGeneratorEntity tokenGenerationEntity);


    // Map TokenGenerationDTO to TokenGenerationEntity
    @Mapping(source = "accountNumber", target = "accountNumber")
    @Mapping(source = "amountPaid", target = "amountPaid")
    @Mapping(source = "tokenGenerated", target = "tokenGenerated")
    @Mapping(source = "serialNumber", target = "serialNumber")
    @Mapping(source = "kiloWatts", target = "kiloWatts")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "expiredAt", target = "expiredAt")
    TokenGeneratorEntity toEntity(TokensGenerationDTO tokenGenerationDTO);
    
}
