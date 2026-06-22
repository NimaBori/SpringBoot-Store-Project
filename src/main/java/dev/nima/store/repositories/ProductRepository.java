package dev.nima.store.repositories;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import dev.nima.store.dtos.ProductSummary;
import dev.nima.store.dtos.ProductSummaryDTO;
import dev.nima.store.entities.Category;
import dev.nima.store.entities.Product;

public interface ProductRepository
    extends CrudRepository<Product, Long>, ProductCriteriaRepository, JpaSpecificationExecutor<Product>,
    PagingAndSortingRepository<Product, Long> {
  // String custom query method
  List<Product> findByNameLikeIgnoreCase(String name);

  // Number custom query method
  List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

  // Nullable custom query method
  // List<Product> findByDescriptionNotNull();

  // Multiple criteria custom query method
  List<Product> findByNameLikeIgnoreCaseAndPriceBetween(String name, BigDecimal minPrice, BigDecimal maxPrice);

  // Sorting custom query method
  List<Product> findByNameLikeIgnoreCaseOrderByPriceAsc(String name);

  // Limiting custom query method
  // JPQL query to find top 5 products by name and sort by price descending
  @Query("SELECT p FROM Product p WHERE UPPER(p.name) LIKE UPPER(:name) ESCAPE '\\' ORDER BY p.price desc")
  List<Product> findTop5ByNameLikeIgnoreCaseOrderByPriceDesc(String name);

  // Find products whose prices are in a given range and sort by name
  // SQL query calling stored procedure to find products by price range and sort
  // by name
  @Procedure(procedureName = "GetProductsByPriceRange")
  List<Product> findProducts(BigDecimal minPrice, BigDecimal maxPrice);

  // SQL query to update price of products by category id
  // @Query(value = "UPDATE products SET price = :newPrice WHERE category_id =
  // :categoryId", nativeQuery = true)
  // JPQL query to update price of products by category id
  @Modifying(clearAutomatically = true)
  @Query("UPDATE Product p SET p.price = :newPrice WHERE p.category.id = :categoryId")
  void updatePriceByCategoryId(@Param("categoryId") Byte categoryId, @Param("newPrice") BigDecimal newPrice);

  List<ProductSummaryDTO> findByCategory(Category category);
}
