package com.example.ElectricityTokenGenerator.repository;

import com.example.ElectricityTokenGenerator.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
}
