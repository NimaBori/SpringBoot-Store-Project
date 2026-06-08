package dev.nima.store.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents a product category (e.g., Electronics, Clothing).
 * Each category can contain multiple products (One-to-Many relationship).
 */
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "categories")
public class Category {
    /**
     * Unique identifier for the category.
     * Uses Byte (TINYINT in DB) to save space for a limited number of categories.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Byte id;

    /**
     * Name of the category.
     */
    @Column(name = "name")
    private String name;

    /**
     * List of products belonging to this category.
     */
    @OneToMany(mappedBy = "category")
    @Builder.Default
    @ToString.Exclude
    private List<Product> products = new ArrayList<>();

    /**
     * Helper method to add a product to this category and maintain bidirectionality.
     */
    public void addProduct(Product product) {
        products.add(product);
        product.setCategory(this);
    }

    /**
     * Helper method to remove a product from this category.
     */
    public void removeProduct(Product product) {
        products.remove(product);
        product.setCategory(null);
    }
}
