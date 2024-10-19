package com.example.ElectricityTokenGenerator.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationsDTO {
    private Long Id;
    private Long userId;
    private String title;
    private String type;
    private String priority;
    private LocalDateTime timestamp;
}
