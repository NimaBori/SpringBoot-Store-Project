ALTER TABLE `wishlist` 
DROP FOREIGN KEY `wishlist_ibfk_2`;

ALTER TABLE `wishlist` 
ADD CONSTRAINT `wishlist_ibfk_2` 
FOREIGN KEY (`product_id`) REFERENCES `products` (`id`);
