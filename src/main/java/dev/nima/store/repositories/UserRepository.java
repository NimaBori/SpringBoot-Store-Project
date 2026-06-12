package dev.nima.store.repositories;

import org.springframework.data.repository.CrudRepository;

import dev.nima.store.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
