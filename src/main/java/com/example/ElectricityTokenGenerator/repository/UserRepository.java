package com.example.ElectricityTokenGenerator.repository;

import com.example.ElectricityTokenGenerator.entity.UsersEntity; // Change to entity package
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UsersEntity, Long> { // Use UsersEntity
}
