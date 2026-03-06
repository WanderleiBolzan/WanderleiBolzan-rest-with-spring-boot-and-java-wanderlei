package br.com.wanderlei.repository;

import br.com.wanderlei.model.Book;
import br.com.wanderlei.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query ("SELECT b FROM Book b WHERE b.author LIKE LOWER(CONCAT ('%',:author,'%'))")
    Page<Book> findBookByauthor(@Param ("author") String author, Pageable pageable);

    // busca por título

    @Query ("SELECT b FROM Book b WHERE b.title LIKE LOWER(CONCAT ('%',:title,'%'))")
    Page<Book> findBookByTitle(@Param ("title") String title, Pageable pageable);

}
