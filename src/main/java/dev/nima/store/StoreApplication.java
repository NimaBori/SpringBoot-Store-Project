package dev.nima.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import dev.nima.store.entities.Address;
import dev.nima.store.entities.Profile;
import dev.nima.store.entities.Tag;
import dev.nima.store.entities.User;

/**
 * Main entry point for the Spring Boot Store application.
 * Configures the application and handles initial startup logic.
 */
@SpringBootApplication
public class StoreApplication {

	public static void main(String[] args) {
		// Bootstrap the Spring application context
		// ApplicationContext context = SpringApplication.run(StoreApplication.class, args);

		/**
		 * Demonstration of JPA Entity relationships using Lombok Builders.
		 * This section manually constructs a User and its associated Profile
		 * to verify relationship mapping.
		 */
		var user = User.builder()
				.name("John Doe")
				.email("john.doe@example.com")
				.password("password123")
				.build();

		var profile = Profile.builder()
				.bio("Software developer with 10 years of experience.")
				.phoneNumber("123-456-7890")
				.dateOfBirth("1990-01-01")
				.loyaltyPoints(100)
				.user(user)
				.build();

		// Establish the bidirectional One-to-One relationship
		user.setProfile(profile);
		profile.setUser(user);

		// Print the user entity (uses Lombok @ToString)
		System.out.println(user);
	}

}