package com.example.ElectricityTokenGenerator.mapper.Users;

import com.example.ElectricityTokenGenerator.dto.Users.userRegistrationDTO;
import com.example.ElectricityTokenGenerator.entity.Users.UserEntities;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserRegistrationMapper {

    // MapStruct will automatically generate the implementation of this interface
    UserRegistrationMapper INSTANCE = Mappers.getMapper(UserRegistrationMapper.class);

    // Map userRegistrationDTO to UserEntities
   @Mapping(source = "userName", target = "userName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "phoneNumber", target = "phoneNumber")
    @Mapping(source = "homeAddress", target = "homeAddress")
    UserEntities toEntity(userRegistrationDTO userRegistrationDTO);

    //Map UserEntity to UserRegistrationDTO
    @Mapping(source = "userName", target = "userName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "phoneNumber", target = "phoneNumber")
    @Mapping(source = "homeAddress", target = "homeAddress")
    userRegistrationDTO toDTO(UserEntities userEntities);

   
}
