package com.example.ElectricityTokenGenerator.services;

import com.example.ElectricityTokenGenerator.models.NotificationsModel; // Import your Notifications model
import com.example.ElectricityTokenGenerator.repository.NotificationsRepository; // Import the repository
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationsService {

    private final NotificationsRepository notificationsRepository;

    @Autowired
    public NotificationsService(NotificationsRepository notificationsRepository) {
        this.notificationsRepository = notificationsRepository;
    }

    // Create a new notification
    public NotificationsModel createNotification(NotificationsModel notification) {
        return notificationsRepository.save(notification);
    }

    // Get a notification by ID
    public Optional<NotificationsModel> getNotificationById(Long id) {
        return notificationsRepository.findById(id);
    }

    // Get all notifications
    public List<NotificationsModel> getAllNotifications() {
        return notificationsRepository.findAll();
    }

    // Delete a notification by ID
    public void deleteNotification(Long id) {
        notificationsRepository.deleteById(id);
    }
}
