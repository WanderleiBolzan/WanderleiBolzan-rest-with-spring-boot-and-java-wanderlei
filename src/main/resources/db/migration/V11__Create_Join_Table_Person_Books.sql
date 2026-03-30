CREATE TABLE IF NOT EXISTS `person_books` (
  `person_id` BIGINT NOT NULL,
  `book_id` BIGINT NOT NULL,
  PRIMARY KEY (`person_id`, `book_id`),
  CONSTRAINT `fk_person_books_person` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`),
  CONSTRAINT `fk_person_books_books` FOREIGN KEY (`book_id`) REFERENCES `books` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;