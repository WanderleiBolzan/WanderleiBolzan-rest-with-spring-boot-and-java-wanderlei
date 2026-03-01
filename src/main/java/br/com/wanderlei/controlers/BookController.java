package br.com.wanderlei.controlers;

import br.com.wanderlei.controlers.docs.BookControllerDocs;
import br.com.wanderlei.data.dto.BookDTO;
import br.com.wanderlei.services.BookServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book/v1")
@Tag(name = "book", description = "Endpoint for management book")
public class BookController implements BookControllerDocs {

    // CORREÇÃO 1: Injetar o serviço aqui no atributo
    @Autowired
    private BookServices services;

    // CORREÇÃO 2: REMOVIDO o @Autowired daqui.
    // Métodos de endpoint (GET, POST, etc) não devem ter @Autowired.
    @GetMapping(value = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public BookDTO findById(@PathVariable(value = "id") Long id) {
        return services.findById(id);
    }

    @Override
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    public List<BookDTO> findAll() {
        return services.findAll();
    }

    @Override
    @PutMapping(produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    public BookDTO update(@RequestBody BookDTO book) {
        return services.update(book);
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        services.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public BookDTO create(@RequestBody BookDTO book) {
        return services.create(book);
    }
}