CREATE TABLE `profiles` (
    `id` bigint NOT NULL,
    `bio` text,
    `phone_number` varchar(255),
    `date_of_birth` date,
    `loyalty_points` int unsigned NOT NULL DEFAULT 0,
    PRIMARY KEY (`id`),
    CONSTRAINT `profiles_ibfk_1` FOREIGN KEY (`id`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;
