# Store Project Developer Manual

This manual provides essential information for developing, testing, and managing the Store application.

## 1. Getting Started

### Prerequisites
- Java 26
- Maven (included as `./mvnw`)
- MySQL (configured in `pom.xml` and `application.yaml`)

## 2. Testing Guide

### Running Unit Tests
To verify the internal logic of your entities and services without involving a database, use the Maven wrapper:

```bash
# Run all tests
./mvnw test

# Run a specific test class
./mvnw test -Dtest=WishlistRelationshipTest
```

### Why we test Entity Relationships
When defining bidirectional relationships in JPA (e.g., User <-> Product), we must ensure that the Java objects are kept in sync in memory. Our unit tests verify:
- **Bidirectionality**: Using helper methods like `user.addToWishlist(product)` correctly updates both sides of the relationship.
- **In-Memory Consistency**: Proves the logic works before we ever touch the database.

## 3. Entity Management

### Bidirectional Relationships
Always use **Helper Methods** to manage relationships. This prevents inconsistent states where one object thinks it's linked but the other doesn't.

**Example: Wishlist**
```java
// In User.java
public void addToWishlist(Product product) {
    wishlist.add(product);
    product.getWishlistedBy().add(this);
}
```

## 4. Database Migrations (Flyway)

We use Flyway for version-controlled database schema changes.

### Adding a Migration
1. Create a new `.sql` file in `src/main/resources/db/migration/`.
2. Follow the naming convention: `V<Number>__<Description>.sql` (e.g., `V5__create_wishlist_table.sql`).
3. Run the application or the Flyway Maven plugin to apply changes.

```bash
# Apply migrations manually
./mvnw flyway:migrate
```

## 5. Troubleshooting

### Lombok Issues
If your IDE or build fails to find `builder()` or `getter/setter` methods, ensure the Lombok annotation processor is configured in your `pom.xml`:

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-compiler-plugin</artifactId>
    <configuration>
        <annotationProcessorPaths>
            <path>
                <groupId>org.projectlombok</groupId>
                <artifactId>lombok</artifactId>
                <version>${lombok.version}</version>
            </path>
        </annotationProcessorPaths>
    </configuration>
</plugin>
```

---
*Created on Wednesday, June 10, 2026.*
