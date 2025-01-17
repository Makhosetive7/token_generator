package com.example.ElectricityTokenGenerator.mapper.Tokens;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.ElectricityTokenGenerator.dto.Tokens.LocalVendorDTO;
import com.example.ElectricityTokenGenerator.entity.Tokens.LocalVendorEntity;

@Mapper
public interface LocalVendorMapper {

    LocalVendorMapper INSTANCE = Mappers.getMapper(LocalVendorMapper.class);

    // Map Entities to DTO
    @Mapping(source = "vendorAccountNumber", target = "vendorAccountNumber")
    @Mapping(source = "purchaseAccountNumber", target = "purchaseAccountNumber")
    @Mapping(source = "vendorTypeEnumerator", target = "vendorTypeEnumerator")
    @Mapping(source = "purchaseAmount", target = "purchaseAmount")
    @Mapping(source = "kiloWatts", target = "kiloWatts")
    @Mapping(source = "createdAt", target = "createdAt")
    LocalVendorDTO toDto(LocalVendorEntity localVendorEntity);

    // Map DTO to Entity
    @Mapping(source = "vendorAccountNumber", target = "vendorAccountNumber")
    @Mapping(source = "purchaseAccountNumber", target = "purchaseAccountNumber")
    @Mapping(source = "vendorTypeEnumerator", target = "vendorTypeEnumerator")
    @Mapping(source = "purchaseAmount", target = "purchaseAmount")
    @Mapping(source = "kiloWatts", target = "kiloWatts")
    @Mapping(source = "createdAt", target = "createdAt")
    LocalVendorEntity toEntity(LocalVendorDTO localVendorDTO);
}
