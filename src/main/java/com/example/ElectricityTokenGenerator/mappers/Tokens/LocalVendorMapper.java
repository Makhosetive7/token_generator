package com.example.ElectricityTokenGenerator.mappers.Tokens;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.ElectricityTokenGenerator.dto.Tokens.LocalVendorDTO;
import com.example.ElectricityTokenGenerator.entity.Tokens.LocalVendorEntity;
import com.example.ElectricityTokenGenerator.entity.Tokens.TokenEntities;

@Mapper
public interface LocalVendorMapper {

    LocalVendorMapper INSTANCE = Mappers.getMapper(LocalVendorMapper.class);

    // Map LocalVendorEntity to LocalVendorDTO
    @Mapping(source = "vendorAccountNumber", target = "vendorAccountNumber")
    @Mapping(source = "purchaseAccountNumber", target = "purchaseAccountNumber")
    @Mapping(source = "vendorTypeEnumerator", target = "vendorTypeEnumerator") 
    @Mapping(source = "purchaseAmount", target = "purchaseAmount")
    @Mapping(source = "createdAt", target = "createdAt")
    LocalVendorDTO toDto(LocalVendorEntity localVendorEntity);

    // Map LocalVendorDTO to LocalVendorEntity
    @Mapping(source = "vendorAccountNumber", target = "vendorAccountNumber")
    @Mapping(source = "purchaseAccountNumber", target = "purchaseAccountNumber")
    @Mapping(source = "vendorTypeEnumerator", target = "vendorTypeEnumerator")
    @Mapping(source = "purchaseAmount", target = "purchaseAmount")
    @Mapping(source = "createdAt", target = "createdAt")
    LocalVendorEntity toEntity(LocalVendorDTO localVendorDTO);

    // Custom mapping method for TokenEntities to String
    default String map(TokenEntities tokenEntities) {
        return tokenEntities.getAccountNumber(); 
    }

    // Custom mapping method for String to TokenEntities
    default TokenEntities map(String accountNumber) {
        TokenEntities tokenEntities = new TokenEntities();
        tokenEntities.setAccountNumber(accountNumber);
        return tokenEntities;
    }
}
