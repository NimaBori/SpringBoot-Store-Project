package dev.nima.store.repositories;

import java.math.BigDecimal;
import java.util.List;

import dev.nima.store.entities.Product;

public interface ProductCriteriaRepository {
  List<Product> findProductsByCriteria(String name, BigDecimal minPrice, BigDecimal maxPrice);
}
