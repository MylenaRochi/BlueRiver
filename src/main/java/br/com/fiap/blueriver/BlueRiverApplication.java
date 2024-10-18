package br.com.fiap.blueriver;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.IOException;

@SpringBootApplication
@EnableJpaRepositories
public class BlueRiverApplication {
	@Bean
	FirebaseMessaging firebaseMessaging() throws IOException {
		GoogleCredentials googleCredentials = GoogleCredentials //
				.fromStream(new ClassPathResource("firebase-credentials.json").getInputStream());

		FirebaseOptions firebaseOptions = FirebaseOptions.builder()
				.setCredentials(googleCredentials)
				.build();

		FirebaseApp firebaseApp = FirebaseApp.initializeApp(firebaseOptions, "test-notification");
		return FirebaseMessaging.getInstance(firebaseApp);
	}

	public static void main(String[] args) {
		SpringApplication.run(BlueRiverApplication.class, args);
	}

}
