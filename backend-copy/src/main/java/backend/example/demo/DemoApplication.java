package backend.example.demo;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class DemoApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();

		// Set them as system properties (Spring will pick these up)
		System.setProperty("CLIENT_ID", dotenv.get("CLIENT_ID"));
		System.setProperty("CLIENT_SECRET", dotenv.get("CLIENT_SECRET"));
		SpringApplication.run(DemoApplication.class, args);
	}

}
