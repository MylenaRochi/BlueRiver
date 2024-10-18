package br.com.fiap.blueriver.notification;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class FakeFirebaseMessaging {

    public void send(Message message) throws FirebaseMessagingException {
        return;
    };
}
