package dev.nima.store.entities;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents a product available in the store.
 * Every product must belong to exactly one category.
 */
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "products")
public class Product {
    /**
     * Unique identifier for the product.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @EqualsAndHashCode.Include
    private Long id;

    /**
     * The display name of the product.
     */
    @Column(name = "name")
    private String name;

    /**
     * The selling price of the product.
     */
    @Column(name = "price")
    private BigDecimal price;

    /**
     * The category this product belongs to.
     * Linked via category_id foreign key.
     */
    @ManyToOne
    @JoinColumn(name = "category_id")
    @ToString.Exclude
    private Category category;

    /**
     * Set of users who have this product in their wishlist.
     */
    @ManyToMany(mappedBy = "wishlist")
    @Builder.Default
    @ToString.Exclude
    private Set<User> wishlistedBy = new HashSet<>();
}
