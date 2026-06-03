package dev.nima.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class StoreApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(StoreApplication.class, args);
		// var orderService = context.getBean(OrderService.class);
		// orderService.placeOrder();
		// var notificationManager = context.getBean(NotificationManager.class);
		// notificationManager.sendNotification("Your order has been placed
		// successfully!");
		var userService = context.getBean(UserService.class);
		userService.registerUser(new User(null, "John Doe", "john.doe@example.com", null));
		userService.registerUser(new User(null, "John Doe", "john.doe@example.com", null));
	}

}