package dev.nima.store.services;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.nima.store.entities.Category;
import dev.nima.store.repositories.ProductRepository;
import dev.nima.store.repositories.UserRepository;

@Service
public class UserService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public UserService(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void updateProductPrices(Byte categoryId, BigDecimal newPrice) {
        productRepository.updatePriceByCategoryId(categoryId, newPrice);
    }

    public void fetchProducts() {
        var products = productRepository.findByCategory(new Category((byte) 1));
        products.forEach(product -> System.out.println(product));

    }

    public void fetchUser() {
        var user = userRepository.findByEmail("john.doe@example.com")
                .orElseThrow(() -> new RuntimeException("User not found"));
        System.out.println(user);
    }
}
