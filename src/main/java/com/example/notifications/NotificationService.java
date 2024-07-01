package com.example.notifications;

import java.util.List;

public interface NotificationService {
    void sendNotification();
    List<String> getNotifications();
}
