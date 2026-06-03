package dev.nima.store;

import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.Map;

@Repository
public class InMemoryUserRepository implements UserRepository {
  private final Map<String, User> users = new HashMap<>();

  @Override
  public void save(User user) {
    if (user != null && user.getEmail() != null) {
      users.put(user.getEmail(), user);
      System.out.println("User saved in memory: " + user.getName() + " (" + user.getEmail() + ")");
    } else {
      System.err.println("Cannot save a null user or a user without an email.");
    }
  }

  @Override
  public User findByEmail(String email) {
    return users.getOrDefault(email, null);
  }
}
