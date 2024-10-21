package com.example.ElectricityTokenGenerator.repository;

import com.example.ElectricityTokenGenerator.entity.NotificationsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationsRepository extends JpaRepository<NotificationsEntity, Long> {

}
