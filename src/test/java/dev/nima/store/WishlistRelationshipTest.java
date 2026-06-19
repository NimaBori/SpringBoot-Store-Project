package dev.nima.store;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import dev.nima.store.entities.Product;
import dev.nima.store.entities.User;

class WishlistRelationshipTest {

    @Test
    void testWishlistRelationship() {
        User user = User.builder()
                .name("Test User")
                .email("test@example.com")
                .build();

        Product product = Product.builder()
                .name("Test Product")
                .price(new BigDecimal("99.99"))
                .build();

        user.addToWishlist(product);

        assertTrue(user.getWishlist().stream().anyMatch(w -> w.getProduct().equals(product)));
        assertTrue(product.getWishlistedBy().stream().anyMatch(w -> w.getUser().equals(user)));

        user.removeFromWishlist(product);
        assertTrue(user.getWishlist().stream().noneMatch(w -> w.getProduct().equals(product)));
        assertTrue(product.getWishlistedBy().stream().noneMatch(w -> w.getUser().equals(user)));
    }
}
