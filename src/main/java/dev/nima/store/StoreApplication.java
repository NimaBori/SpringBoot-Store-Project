package dev.nima.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import dev.nima.store.entities.Address;
import dev.nima.store.entities.Profile;
import dev.nima.store.entities.Tag;
import dev.nima.store.entities.User;

@SpringBootApplication
public class StoreApplication {

	public static void main(String[] args) {
		// ApplicationContext context = SpringApplication.run(StoreApplication.class,
		// args);
		// var orderService = context.getBean(OrderService.class);
		// orderService.placeOrder();
		// var notificationManager = context.getBean(NotificationManager.class);
		// notificationManager.sendNotification("Your order has been placed
		// successfully!");
		// var userService = context.getBean(UserService.class);
		// userService.registerUser(new User(null, "John Doe", "john.doe@example.com",
		// null));
		// userService.registerUser(new User(null, "John Doe", "john.doe@example.com",
		// null));

		var user = User.builder()
				.name("John Doe")
				.email("john.doe@example.com")
				.password("password123")
				.build();

		// user.addTag("tag1");
		var profile = Profile.builder()
				.bio("Software developer with 10 years of experience.")
				.phoneNumber("123-456-7890")
				.dateOfBirth("1990-01-01")
				.loyaltyPoints(100)
				.user(user)
				.build();

		user.setProfile(profile);
		profile.setUser(user);

		System.out.println(user);
	}

}