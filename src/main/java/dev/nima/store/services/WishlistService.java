package dev.nima.store.services;

import java.util.stream.StreamSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.nima.store.entities.User;
import dev.nima.store.repositories.ProductRepository;
import dev.nima.store.repositories.UserRepository;

@Service
public class WishlistService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public WishlistService(UserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public void updateWishlist() {
        System.out.println("--- Fetching User ---");
        User user = userRepository.findById(1L).orElseThrow(() -> new RuntimeException("User not found"));

        System.out.println("--- Fetching All Products ---");
        var allProducts = productRepository.findAll();

        System.out.println("--- Adding Products to Wishlist ---");
        StreamSupport.stream(allProducts.spliterator(), false)
                .forEach(user::addToWishlist);

        System.out.println("--- Saving User ---");
        userRepository.save(user);
        System.out.println("Wishlist updated successfully!");
    }

    @Transactional
    public void deleteProduct(Long productId) {
        System.out.println("--- Attempting to delete Product ID: " + productId + " ---");
        productRepository.deleteById(productId);
        System.out.println("Product deleted successfully!");
    }
}
