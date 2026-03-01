package br.com.wanderlei.unitTests.mapper.mocks;

import br.com.wanderlei.data.dto.BookDTO;
import br.com.wanderlei.model.Book;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MockBook {
    public Book mockEntity() {
        return mockEntity(0);
    }
    
    public BookDTO mockDTO() {
        return mockDTO(0);
    }
    
    public List<Book> mockEntityList() {
        List<Book> books = new ArrayList<Book>();
        for (int i = 0; i < 14; i++) {
            books.add(mockEntity(i));
        }
        return books;
    }

    public List<BookDTO> mockDTOList() {
        List<BookDTO> books = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            books.add(mockDTO(i));
        }
        return books;
    }
    
    public Book mockEntity(Integer number) {
        Book book = new Book();
        book.setAuthor ("Author Test" + number);
        book.setLaunch_date (new Date ());
        book.setPrice((double) (number + 100));
        book.setTitle ("Título Test" + number);
        return book;
    }

    public BookDTO mockDTO(Integer number) {
        BookDTO book = new BookDTO();
        book.setAuthor ("Author Test" + number);
        book.setLaunch_date (new Date());
        book.setId(number.longValue());
        book.setPrice((double) (number = 100));
        book.setTitle ("Título Teste" + number);
        return book;
    }

}