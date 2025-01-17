package com.example.ElectricityTokenGenerator.mapper.Users;

import com.example.ElectricityTokenGenerator.entity.Users.UserEntities;
import com.example.ElectricityTokenGenerator.dto.Users.UsersDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    // Map Entity to DTO
    @Mapping(source = "userName", target = "userName")
    @Mapping(source = "accountNumber", target = "accountNumber")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "phoneNumber", target = "phoneNumber")
    @Mapping(source = "homeAddress", target = "homeAddress")
    @Mapping(source = "transactionHistory", target = "transactionHistory")
    @Mapping(source = "donationHistory", target = "donationHistory")
    UsersDTO toDto(UserEntities userEntities);

    // Map DTO to Entity
    @Mapping(source = "userName", target = "userName")
    @Mapping(source = "accountNumber", target = "accountNumber")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "phoneNumber", target = "phoneNumber")
    @Mapping(source = "homeAddress", target = "homeAddress")
    @Mapping(source = "transactionHistory", target = "transactionHistory")
    @Mapping(source = "donationHistory", target = "donationHistory")
    UserEntities toEntity(UsersDTO usersDTO);
}
