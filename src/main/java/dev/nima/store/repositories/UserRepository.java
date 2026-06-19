package dev.nima.store.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import dev.nima.store.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {
  @EntityGraph(attributePaths = "tags")
  Optional<User> findByEmail(String email);
}
