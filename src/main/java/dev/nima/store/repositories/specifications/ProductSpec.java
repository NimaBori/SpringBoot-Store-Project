package dev.nima.store.repositories.specifications;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import dev.nima.store.entities.Product;

public class ProductSpec {
  public static Specification<Product> hasName(String name) {
    return (root, query, criteriaBuilder) -> {
      if (name == null || name.isEmpty()) {
        return criteriaBuilder.conjunction();
      }
      return criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    };
  }

  public static Specification<Product> hasPriceBetween1(BigDecimal minPrice, BigDecimal maxPrice) {
    return (root, query, criteriaBuilder) -> {
      if (minPrice == null && maxPrice == null) {
        return criteriaBuilder.conjunction();
      } else if (minPrice != null && maxPrice != null) {
        return criteriaBuilder.between(root.get("price"), minPrice, maxPrice);
      } else if (minPrice != null) {
        return criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
      } else {
        return criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
      }
    };
  }

  public static Specification<Product> hasPriceBetween(BigDecimal minPrice, BigDecimal maxPrice) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'hasPriceBetween'");
  }
}
