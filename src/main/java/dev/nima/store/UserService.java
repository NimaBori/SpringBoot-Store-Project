package dev.nima.store;

import org.springframework.stereotype.Service;

@Service
public class UserService {
  private final UserRepository userRepository;
  private final NotificationService notificationService;

  public UserService(UserRepository userRepository, NotificationService notificationService) {
    this.userRepository = userRepository;
    this.notificationService = notificationService;
  }

  public void registerUser(User user) {
    if (userRepository.findByEmail(user.getEmail()) != null) {
      System.err.println("User with email " + user.getEmail() + " already exists.");
      return;
    }
    userRepository.save(user);
    notificationService.sendNotification("Welcome to our store, " + user.getName() + "!", user.getEmail());
  }
}
