package com.example.ElectricityTokenGenerator.mappers.Tokens;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.ElectricityTokenGenerator.dto.Tokens.TokensGeneratorDTO;
import com.example.ElectricityTokenGenerator.entity.Tokens.TokenGenerator;

@Mapper(componentModel = "spring")
public interface TokenGenerationMapper {

    TokenGenerationMapper INSTANCE = Mappers.getMapper(TokenGenerationMapper.class);

        // Map TokensGeneratorDTO to TokenGenerator entity
        @Mapping(source = "accountNumber", target = "accountNumber.accountNumber")
        @Mapping(source = "amount", target = "amount") 
        @Mapping(target = "id", ignore = true) 
        @Mapping(target = "generatedTokenCode", ignore = true) 
        @Mapping(target = "kiloWatts", ignore = true) 
        @Mapping(target = "generationDate", ignore = true)
        @Mapping(target = "status", ignore = true) 
        TokenGenerator toEntity(TokensGeneratorDTO tokensGeneratorDTO);
    
        // Map TokenGenerator entity to TokensGeneratorDTO
        @Mapping(source = "accountNumber.accountNumber", target = "accountNumber")
        @Mapping(source = "amount", target = "amount") 
        TokensGeneratorDTO toDto(TokenGenerator tokenGenerator);
    
}
