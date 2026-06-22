package dev.nima.store.services;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.nima.store.entities.Category;
import dev.nima.store.entities.Product;
import dev.nima.store.repositories.ProductRepository;
import dev.nima.store.repositories.UserRepository;
import dev.nima.store.repositories.specifications.ProductSpec;

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

    public void fetchProductsByCriteria() {
        var products = productRepository.findProductsByCriteria(null, BigDecimal.valueOf(1), BigDecimal.valueOf(10));
        products.forEach(product -> System.out.println(product));
    }

    public void fetchProductsBySpecification(String name, BigDecimal minPrice, BigDecimal maxPrice) {
        Specification<Product> spec = (root, query, cb) -> cb.conjunction();

        if (name != null) {
            spec = spec.and(ProductSpec.hasName(name));
        }
        if (minPrice != null || maxPrice != null) {
            spec = spec.and(ProductSpec.hasPriceBetween(minPrice, maxPrice));
        }
        var products = productRepository.findAll(spec);
        products.forEach(product -> System.out.println(product));
    }

    public void fetchSortedProducts() {
        Sort sort = Sort.by("name").ascending().and(Sort.by("price").descending());
        productRepository.findAll(sort).forEach(System.out::println);
    }

    public void fetchPaginatedProducts(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<Product> productPage = productRepository.findAll(pageable);
        productPage.getContent().forEach(System.out::println);
        System.out.println("Total pages: " + productPage.getTotalPages());
        System.out.println("Total elements: " + productPage.getTotalElements());
    }
}
