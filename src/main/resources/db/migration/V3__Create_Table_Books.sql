CREATE TABLE IF NOT EXISTS `books` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `author` longtext,
  `launch_date` datetime(6) NOT NULL,
  `price` decimal(65,2) NOT NULL,
  `title` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;