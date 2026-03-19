package br.com.wanderlei.controllers;

import br.com.wanderlei.controllers.docs.BookControllerDocs;
import br.com.wanderlei.data.dto.BookDTO;
import br.com.wanderlei.services.BookServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    public ResponseEntity<PagedModel<EntityModel<BookDTO>>> findAll(
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="size", defaultValue="12") Integer size,
            @RequestParam(value="direction", defaultValue="asc") String direction
    ) {

        var sortDirection = "desc".equalsIgnoreCase (direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pagebook = PageRequest.of (page, size, Sort.by (sortDirection, "author"));
        return ResponseEntity.ok (services.findAll(pagebook));
    }

    @GetMapping(value = "/findBookByAuthor/{author}", produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<PagedModel<EntityModel<BookDTO>>> findByAuthor(
            @PathVariable("author") String author,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "12") Integer size,
            @RequestParam(value = "direction", defaultValue = "asc") String direction
    ) {
        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "author"));
        return ResponseEntity.ok(services.findByAuthor(author, pageable));
    }

    @GetMapping(value = "/findBookByTitle/{title}", produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<PagedModel<EntityModel<BookDTO>>> findByTitle(
            @PathVariable("title") String title,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "12") Integer size,
            @RequestParam(value = "direction", defaultValue = "asc") String direction
    ) {
        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "title"));
        return ResponseEntity.ok(services.findByTitle(title, pageable));
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