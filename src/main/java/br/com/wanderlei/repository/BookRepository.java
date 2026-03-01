package br.com.wanderlei.repository;

import br.com.wanderlei.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
