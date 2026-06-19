DELIMITER //

CREATE PROCEDURE GetProductsByPriceRange(
    IN minPrice DECIMAL(10, 2),
    IN maxPrice DECIMAL(10, 2)
)
BEGIN
    SELECT p.id, p.name, p.price, p.category_id FROM products p
    WHERE p.price BETWEEN minPrice AND maxPrice
    ORDER BY p.name;
END //

DELIMITER ;
