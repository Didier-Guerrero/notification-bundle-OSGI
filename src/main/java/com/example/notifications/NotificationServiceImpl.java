package com.example.notifications;

import java.util.ArrayList;
import java.util.List;

public class NotificationServiceImpl implements NotificationService {

  private static final int RAM_THRESHOLD = 80;
  private static final int GPU_THRESHOLD = 80;
  private static final int CPU_THRESHOLD = 80;
  private List<String> notifications = new ArrayList<>();

  @Override
  public void sendNotification() {
      int ramUsage = simulateValue(30, 90);
      int gpuUsage = simulateValue(20, 90);
      int cpuUsage = simulateValue(10, 90);

      StringBuilder message = new StringBuilder();
      boolean isCritical = false;

      if (ramUsage > RAM_THRESHOLD) {
          message.append(String.format("Valores de RAM: %d%% exceden el umbral de %d%% - Requiere acción inmediata!\n", ramUsage, RAM_THRESHOLD));
          isCritical = true;
      } else {
          message.append(String.format("Valores de RAM: %d%% dentro de rangos normales\n", ramUsage));
      }

      if (gpuUsage > GPU_THRESHOLD) {
          message.append(String.format("Valores de GPU: %d%% exceden el umbral de %d%% - Requiere acción inmediata!\n", gpuUsage, GPU_THRESHOLD));
          isCritical = true;
      } else {
          message.append(String.format("Valores de GPU: %d%% dentro de rangos normales\n", gpuUsage));
      }

      if (cpuUsage > CPU_THRESHOLD) {
          message.append(String.format("Valores de CPU: %d%% exceden el umbral de %d%% - Requiere acción inmediata!\n", cpuUsage, CPU_THRESHOLD));
          isCritical = true;
      } else {
          message.append(String.format("Valores de CPU: %d%% dentro de rangos normales\n", cpuUsage));
      }

      if (!isCritical) {
          message.append("\nTodo normal, se ha enviado el correo para notificar que no hay novedad alguna.");
      }

      String notification = message.toString();
      notifications.add(notification);
      System.out.println(notification);
  }

  @Override
  public List<String> getNotifications() {
      List<String> formattedNotifications = new ArrayList<>();
      for (int i = 0; i < notifications.size(); i++) {
          formattedNotifications.add("-----------------Mensaje " + (i + 1) + "-----------------\n" + notifications.get(i));
      }
      return formattedNotifications;
  }

  private int simulateValue(int min, int max) {
      return min + (int)(Math.random() * ((max - min) + 1));
  }
}
