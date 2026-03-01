package br.com.wanderlei.mapper.custom;

import br.com.wanderlei.data.dto.BookDTO;
import br.com.wanderlei.model.Book;
import org.springframework.stereotype.Service;

@Service
public class BookMapper {

    public BookDTO convertEntityToDTO(Book book) {
        BookDTO dto = new BookDTO ();

        dto.setId (book.getId());
        dto.setAuthor (book.getAuthor());
        dto.setLaunch_date (book.getLaunch_date ());
        dto.setPrice (book.getPrice());
        dto.setTitle (book.getTitle());

        return dto;

    }

    public BookDTO convertDTOToEntity(BookDTO book) {

        BookDTO dto = new BookDTO ();

        dto.setId(book.getId());
        dto.setAuthor(book.getAuthor());
        dto.setLaunch_date (book.getLaunch_date ());
        dto.setPrice(book.getPrice());
        dto.setTitle(book.getTitle());

        return dto;
    }
}
