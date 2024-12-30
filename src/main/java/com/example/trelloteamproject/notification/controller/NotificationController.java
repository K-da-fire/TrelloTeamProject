package com.example.trelloteamproject.notification.controller;

import com.example.trelloteamproject.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    public void notifySlack(@RequestBody String message) {
        notificationService.sendMessageToSlack(message);
    }
}
