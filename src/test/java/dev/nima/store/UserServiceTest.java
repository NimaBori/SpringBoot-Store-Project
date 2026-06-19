package dev.nima.store;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import dev.nima.store.entities.Category;
import dev.nima.store.entities.Product;
import dev.nima.store.entities.User;
import dev.nima.store.repositories.CategoryRepository;
import dev.nima.store.repositories.ProductRepository;
import dev.nima.store.repositories.UserRepository;
import dev.nima.store.services.UserService;

@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void testUpdateProductPrices() {
        User user = User.builder()
                .name("John Doe")
                .email("john.doe@example.com")
                .password("password123")
                .build();
        userRepository.save(user);

        Category category = Category.builder().name("Electronics").build();
        final Category savedCategory = categoryRepository.save(category);

        Product p1 = Product.builder().name("Laptop").price(new BigDecimal("1000.00")).category(savedCategory).build();
        Product p2 = Product.builder().name("Phone").price(new BigDecimal("500.00")).category(savedCategory).build();
        productRepository.save(p1);
        productRepository.save(p2);

        BigDecimal newPrice = new BigDecimal("1200.00");
        userService.updateProductPrices(savedCategory.getId(), newPrice);

        // Refresh or re-fetch products to see changes (though JPQL update should be
        // reflected if not cached)
        productRepository.findAll().forEach(p -> {
            if (p.getCategory().getId().equals(savedCategory.getId())) {
                assertEquals(0, newPrice.compareTo(p.getPrice()), "Price for " + p.getName() + " should be updated");
            }
        });

        userService.fetchUser(); // This will print the updated product prices to the console
    }
}
