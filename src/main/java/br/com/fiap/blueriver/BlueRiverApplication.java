package br.com.fiap.blueriver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.IOException;

@SpringBootApplication
@EnableJpaRepositories
public class BlueRiverApplication {
	public static void main(String[] args) {
		SpringApplication.run(BlueRiverApplication.class, args);
	}

}
