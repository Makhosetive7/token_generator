package com.example.ElectricityTokenGenerator.mappers.Users;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.ElectricityTokenGenerator.dto.Users.UserDTO;
import com.example.ElectricityTokenGenerator.entity.Users.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    // Map UserDTO to User entity
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "phoneNumber", target = "phoneNumber")
    @Mapping(source = "accountNumber", target = "accountNumber")
    @Mapping(source = "kiloWatts", target = "kiloWatts")
    @Mapping(source = "role", target = "role")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "homeAddress", ignore = true)
    @Mapping(target = "amountPaid", ignore = true)
    @Mapping(target = "localVendorTransactions", ignore = true)
    @Mapping(target = "donations", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    User toEntity(UserDTO userDTO);

    // Map User entity to UserDTO
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "phoneNumber", target = "phoneNumber")
    @Mapping(source = "accountNumber", target = "accountNumber")
    @Mapping(source = "kiloWatts", target = "kiloWatts")
    @Mapping(source = "role", target = "role")
    UserDTO toDto(User user);
}
