package com.example.ElectricityTokenGenerator.controllers.NotificationsController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import com.example.ElectricityTokenGenerator.entity.NotificationsEntity;
import com.example.ElectricityTokenGenerator.services.NotificationsService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@Controller
@RequestMapping("/notifications/")
public class Notifications {

    private final NotificationsService notificationsService;

    @Autowired
    public Notifications(NotificationsService notificationsService) {
        this.notificationsService = notificationsService;
    }

    // get all notifications
    @GetMapping("/")
    public List<NotificationsEntity> getAllNotifications() {
        return notificationsService.getAllNotifications();
    }

    // Get notification by ID
    @GetMapping("/{id}")
    public ResponseEntity<NotificationsEntity> getNotificationById(@PathVariable Long id) {
        Optional<NotificationsEntity> notification = notificationsService.getNotificationById(id);
        return notification.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // delete available user by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        notificationsService.deleteNotification(id);
        return ResponseEntity.noContent().build();
    }

}
