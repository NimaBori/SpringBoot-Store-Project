CREATE TABLE `users` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'Primary Key',
    `name` varchar(255) NOT NULL,
    `email` varchar(255) NOT NULL,
    `password` varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = 'User Table';

CREATE TABLE `addresses` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `street` varchar(255) NOT NULL,
    `city` varchar(255) NOT NULL,
    `zip` varchar(255) NOT NULL,
    `user_id` bigint NOT NULL,
    PRIMARY KEY (`id`),
    KEY `user_id` (`user_id`),
    CONSTRAINT `addresses_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;