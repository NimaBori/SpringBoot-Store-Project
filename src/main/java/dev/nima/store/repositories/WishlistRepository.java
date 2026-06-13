package dev.nima.store.repositories;

import org.springframework.data.repository.CrudRepository;
import dev.nima.store.entities.Wishlist;

public interface WishlistRepository extends CrudRepository<Wishlist, Long> {
}
