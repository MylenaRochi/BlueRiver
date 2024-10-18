package br.com.fiap.blueriver.notification;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@Lazy
@Setter(onMethod_ = @Autowired)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NotificationServiceImpl implements NotificationService {

    FakeFirebaseMessaging firebaseMessaging;

    @Override
    public void sendNotification(String deviceId, String title, String body) {

        Notification notification = Notification.builder() //
                .setBody(body) //
                .setTitle(title) //
                .build();

        Message message = Message.builder()
                .setToken(deviceId)
                .setNotification(notification)
                .build();

        try {
            firebaseMessaging.send(message);
        } catch (FirebaseMessagingException e) {
            log.error(e);
        }
    }
}
