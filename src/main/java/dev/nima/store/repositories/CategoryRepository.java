package dev.nima.store.repositories;

import org.springframework.data.repository.CrudRepository;
import dev.nima.store.entities.Category;

public interface CategoryRepository extends CrudRepository<Category, Byte> {
}
