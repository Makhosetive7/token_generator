package com.example.ElectricityTokenGenerator.mappers.Users;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.ElectricityTokenGenerator.dto.Users.UserRegistrationDTO;
import com.example.ElectricityTokenGenerator.entity.Users.User;
@Mapper(componentModel = "spring")
public interface UserRegistrationMapper {

    UserRegistrationMapper INSTANCE = Mappers.getMapper(UserRegistrationMapper.class);

    // Map UserRegistrationDTO to User entity
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "phoneNumber", target = "phoneNumber")
    @Mapping(source = "accountNumber", target = "accountNumber")
    @Mapping(source = "homeAddress", target = "homeAddress")
    @Mapping(target = "amountPaid", ignore = true)
    @Mapping(target = "kiloWatts", ignore = true)
    User toEntity(UserRegistrationDTO userRegistrationDTO);

    // Map User entity to UserRegistrationDTO
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "phoneNumber", target = "phoneNumber")
    @Mapping(source = "accountNumber", target = "accountNumber")
    @Mapping(source = "homeAddress", target = "homeAddress")
    UserRegistrationDTO toDto(User user);

}
