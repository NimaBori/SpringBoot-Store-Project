package dev.nima.store.repositories;

import org.springframework.data.repository.CrudRepository;
import dev.nima.store.entities.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
