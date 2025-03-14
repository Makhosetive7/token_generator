package com.example.ElectricityTokenGenerator.mappers.Tokens;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.ElectricityTokenGenerator.dto.Tokens.LocalVendorDTO;
import com.example.ElectricityTokenGenerator.entity.Tokens.LocalVendor;

@Mapper(componentModel = "spring")
public interface LocalVendorMapper {

    LocalVendorMapper INSTANCE = Mappers.getMapper(LocalVendorMapper.class);

    // Map LocalVendorDTO to LocalVendor entity
    @Mapping(source = "senderAccount", target = "sender.accountNumber")
    @Mapping(source = "vendorTypeEnumerator", target = "localVendor")
    @Mapping(source = "amount", target = "amount")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dateTime", ignore = true)
    @Mapping(target = "receiverAccountNumber", ignore = true)
    LocalVendor toEntity(LocalVendorDTO localVendorDTO);

    // Map LocalVendor entity to LocalVendorDTO
    @Mapping(source = "sender.accountNumber", target = "senderAccount")
    @Mapping(source = "localVendor", target = "vendorTypeEnumerator")
    @Mapping(source = "amount", target = "amount")
    LocalVendorDTO toDto(LocalVendor localVendor);

}
