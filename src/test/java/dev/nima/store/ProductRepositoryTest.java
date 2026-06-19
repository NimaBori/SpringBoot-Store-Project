package dev.nima.store;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import dev.nima.store.entities.Category;
import dev.nima.store.entities.Product;
import dev.nima.store.repositories.CategoryRepository;
import dev.nima.store.repositories.ProductRepository;

@SpringBootTest
@Transactional
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void testFindProductsByPriceRangeStoredProcedure() {
        Category category = Category.builder().name("Test Electronics").build();
        final Category savedCategory = categoryRepository.save(category);

        Product p1 = Product.builder().name("Zebra Phone").price(new BigDecimal("600.00")).category(savedCategory).build();
        Product p2 = Product.builder().name("Banana Phone").price(new BigDecimal("700.00")).category(savedCategory).build();
        Product p3 = Product.builder().name("Apple Phone").price(new BigDecimal("500.00")).category(savedCategory).build();
        Product p4 = Product.builder().name("Computer").price(new BigDecimal("1200.00")).category(savedCategory).build();

        productRepository.save(p1);
        productRepository.save(p2);
        productRepository.save(p3);
        productRepository.save(p4);

        // Fetch products between 550 and 750 (should return "Banana Phone" and "Zebra Phone")
        // Sorted by name, "Banana Phone" comes before "Zebra Phone"
        List<Product> products = productRepository.findProducts(new BigDecimal("550.00"), new BigDecimal("750.00"));

        assertNotNull(products);
        assertEquals(2, products.size());
        assertEquals("Banana Phone", products.get(0).getName());
        assertEquals("Zebra Phone", products.get(1).getName());
    }
}
