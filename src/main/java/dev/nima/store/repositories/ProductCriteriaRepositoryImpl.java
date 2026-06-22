package dev.nima.store.repositories;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import dev.nima.store.entities.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Repository
public class ProductCriteriaRepositoryImpl implements ProductCriteriaRepository {
  @PersistenceContext
  private final EntityManager entityManager;

  @Override
  public List<Product> findProductsByCriteria(String name, BigDecimal minPrice, BigDecimal maxPrice, String categoryName) {
    // Implementation using Criteria API or JPQL to filter products based on the
    // criteria
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<Product> query = cb.createQuery(Product.class);
    Root<Product> product = query.from(Product.class);
    // This is a placeholder implementation and should be replaced with actual logic
    List<Predicate> predicates = new ArrayList<>();
    if (name != null && !name.isEmpty()) {
      predicates.add(cb.like(cb.upper(product.get("name")), "%" + name.toUpperCase() + "%"));
    }
    if (minPrice != null) {
      predicates.add(cb.greaterThanOrEqualTo(product.get("price"), minPrice));
    }
    if (maxPrice != null) {
      predicates.add(cb.lessThanOrEqualTo(product.get("price"), maxPrice));
    }
    if (categoryName != null && !categoryName.isEmpty()) {
      predicates.add(cb.equal(product.get("category").get("name"), categoryName));
    }
    query.select(product).where(predicates.toArray(new Predicate[0]));
    return entityManager.createQuery(query).getResultList(); // Return the filtered list of products
  }

}
