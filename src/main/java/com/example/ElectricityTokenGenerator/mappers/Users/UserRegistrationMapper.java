package com.example.ElectricityTokenGenerator.mappers.Users;

import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.ElectricityTokenGenerator.dto.Users.userRegistrationDTO;
import com.example.ElectricityTokenGenerator.entity.Users.UserEntities;

public interface UserRegistrationMapper {

    UserRegistrationMapper INSTANCE = Mappers.getMapper(UserRegistrationMapper.class);

    // Map UserRegistrationDTO to UserEntity
    @Mapping(source = "userName", target = "userName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "phoneNumber", target = "phoneNumber")
    @Mapping(source = "homeAddress", target = "homeAddress")
    UserEntities toEntity(userRegistrationDTO userRegistrationDTO);

    // Map UserEntity to UserRegistrationDTO
    @Mapping(source = "userName", target = "userName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "phoneNumber", target = "phoneNumber")
    @Mapping(source = "homeAddress", target = "homeAddress")
    userRegistrationDTO toUserRegistrationDTO(UserEntities userEntities);

    
}
