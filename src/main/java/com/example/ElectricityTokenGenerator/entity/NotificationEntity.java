package com.example.ElectricityTokenGenerator.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationEntity {
    private Long Id;
    private Long userId;
    private String title;
    private String type;
    private String priority;
    private LocalDateTime timestamp;
    
}
