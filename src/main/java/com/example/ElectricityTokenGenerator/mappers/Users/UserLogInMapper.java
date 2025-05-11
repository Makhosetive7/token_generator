package com.example.ElectricityTokenGenerator.mappers.Users;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.ElectricityTokenGenerator.dto.Users.UserLoginDTO;
import com.example.ElectricityTokenGenerator.entity.Users.User;

@Mapper(componentModel = "spring")
public interface UserLogInMapper {

    UserLogInMapper INSTANCE = Mappers.getMapper(UserLogInMapper.class);

    // Map UserLogInDTO to User entity
    @Mapping(source = "password", target = "password")
    @Mapping(source   = "email", target = "email")
     User toEntity(UserLoginDTO userLogInDTO);


     //Map User entity to UserLogInDTO
    @Mapping(source = "password", target = "password")
    @Mapping(source = "email", target = "email")
    UserLoginDTO toDto(User user);
    
}
