package com.example.trelloteamproject.notification.service;

import org.springframework.stereotype.Service;

@Service
public interface NotificationService {
    void sendMessageToSlack(String message);
}
