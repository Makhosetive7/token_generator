package com.example.ElectricityTokenGenerator.mappers.Tokens;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.ElectricityTokenGenerator.dto.Tokens.LocalVendorDTO;
import com.example.ElectricityTokenGenerator.entity.Tokens.LocalVendor;

@Mapper
public interface LocalVendorMapper {

    LocalVendorMapper INSTANCE = Mappers.getMapper(LocalVendorMapper.class);

    // Map LocalVendor entity to LocalVendorDTO
    @Mapping(source = "vendorAccountNumber.accountNumber", target = "vendorAccountNumber")
    @Mapping(source = "purchaseAccountNumber", target = "purchaseAccountNumber")
    @Mapping(source = "vendorTypeEnumerator", target = "vendorTypeEnumerator")
    @Mapping(source = "convertedValue", target = "convertedValue")
    @Mapping(source = "purchaseAmount", target = "purchaseAmount")
    @Mapping(source = "createdAt", target = "createdAt")
    LocalVendorDTO toDto(LocalVendor localVendor);

    // Map LocalVendorDTO to LocalVendor entity
    @Mapping(source = "vendorAccountNumber", target = "vendorAccountNumber.accountNumber")
    @Mapping(source = "purchaseAccountNumber", target = "purchaseAccountNumber")
    @Mapping(source = "vendorTypeEnumerator", target = "vendorTypeEnumerator")
    @Mapping(source = "convertedValue", target = "convertedValue")
    @Mapping(source = "purchaseAmount", target = "purchaseAmount")
    @Mapping(source = "createdAt", target = "createdAt")
    LocalVendor toEntity(LocalVendorDTO localVendorDTO);
}