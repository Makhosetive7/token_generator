package com.example.ElectricityTokenGenerator.mappers.Users;

import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.ElectricityTokenGenerator.dto.Users.UsersDTO;
import com.example.ElectricityTokenGenerator.entity.Users.UserEntities;

public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    // Map UserEntity to UserDTO
    @Mapping(source = "userName", target = "userName")
    @Mapping(source = "accountNumber", target = "accountNumber")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "phoneNumber", target = "phoneNumber")
    @Mapping(source = "homeAddress", target = "homeAddress")
    @Mapping(source = "transfer_history", target = "transfer_history")
    @Mapping(source = "donation_history", target = "donation_history")
    UsersDTO toDto(UserEntities userEntities);

    // Map UserDTO to UserEntity
    @Mapping(source = "userName", target = "userName")
    @Mapping(source = "accountNumber", target = "accountNumber")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "phoneNumber", target = "phoneNumber")
    @Mapping(source = "homeAddress", target = "homeAddress")
    @Mapping(source = "transfer_history", target = "transfer_history")
    @Mapping(source = "donation_history", target = "donation_history")
    UserEntities toEntity(UsersDTO userDTO);
}
