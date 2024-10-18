package br.com.fiap.blueriver.notification;

public interface NotificationService {

    void sendNotification(String deviceId, String title, String body);

}
