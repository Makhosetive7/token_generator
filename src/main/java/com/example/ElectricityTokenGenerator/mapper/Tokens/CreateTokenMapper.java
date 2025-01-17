package com.example.ElectricityTokenGenerator.mapper.Tokens;

import com.example.ElectricityTokenGenerator.dto.Tokens.TokenTransferDTO;
import com.example.ElectricityTokenGenerator.dto.Tokens.TokensGenerationDTO;
import com.example.ElectricityTokenGenerator.entity.Tokens.CreateTokenEntities;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CreateTokenMapper {

    // Get the instance of the mapper
    CreateTokenMapper INSTANCE = Mappers.getMapper(CreateTokenMapper.class);

    // Map Entity to DTO
    @Mapping(source = "accountNumber", target = "accountNumber")
    @Mapping(source = "amountPaid", target = "amountPaid")
    @Mapping(source = "createdAt", target = "createdAt")
    TokensGenerationDTO toDto(CreateTokenEntities createTokenEntities);

    // Map DTO to Entity
    @Mapping(source = "accountNumber", target = "accountNumber")
    @Mapping(source = "amountPaid", target = "amountPaid")
    @Mapping(source = "createdAt", target = "createdAt")
    CreateTokenEntities toEntity(TokenTransferDTO tokenTransferDTO);
}
