package br.com.wanderlei.services;

import br.com.wanderlei.controlers.BookController;
import br.com.wanderlei.controlers.PersonController;
import br.com.wanderlei.data.dto.BookDTO;
import br.com.wanderlei.data.dto.PersonDTO;
import br.com.wanderlei.exception.RequiredObjectIsNullException;
import br.com.wanderlei.exception.ResourceNotFoundException;
import br.com.wanderlei.mapper.custom.BookMapper;
import br.com.wanderlei.model.Book;
import br.com.wanderlei.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.wanderlei.mapper.ObjectMapper.parseListObject;
import static br.com.wanderlei.mapper.ObjectMapper.parseObject;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Service
public class BookServices {
    private Logger logger = LoggerFactory.getLogger(BookServices.class.getName ());
    @Autowired
    BookRepository repository;

    @Autowired
    PagedResourcesAssembler<BookDTO> assembler;

    @Autowired
    BookMapper converter;

    public PagedModel<EntityModel<BookDTO>> findAll(Pageable pageable) {

        logger.info("Finding all People");

        var books  = repository.findAll(pageable);

        var peopleWithLinks =  books.map(book ->  {
            var dto = parseObject(book, BookDTO.class);
            addHateoasLinks(dto);
            return dto;
        });


        Link findAllLink = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(BookController.class)
                        .findAll(pageable.getPageNumber (),
                                pageable.getPageSize(),
                                String.valueOf (pageable.getSort())))
                .withSelfRel();
        return assembler.toModel (peopleWithLinks,
                findAllLink);
    }


    public BookDTO findById(Long id) {
        logger.info("Finding one BookDTO");

        var entity =  repository.findById (id)
                .orElseThrow (() -> new ResourceNotFoundException("No Records found for this ID "));

        var dto = parseObject(entity, BookDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public BookDTO create(BookDTO book) {

        if (book == null) throw new RequiredObjectIsNullException ();

        logger.info("Creating one BookDTO");

        var entity = parseObject(book, Book.class);

        var dto = parseObject(repository.save(entity), BookDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public BookDTO update(BookDTO book) {


        if (book == null) throw new RequiredObjectIsNullException ();

        logger.info("updating one PersonDTO");
        Book entity =  repository.findById (book.getId ( ))
                .orElseThrow (() -> new ResourceNotFoundException("No Records found for this ID "));

        entity.setAuthor (book.getAuthor ());
        entity.setLaunchDate (book.getLaunchDate ());
        entity.setPrice (book.getPrice ());
        entity.setTitle (book.getTitle ());

        var dto =  parseObject(repository.save(entity), BookDTO.class);

        addHateoasLinks(dto);
        return dto;
    }

    public void delete(Long id) {
        logger.info("deleting one BookDTO");

        Book entity =  repository.findById (id)
                .orElseThrow (() -> new ResourceNotFoundException("No Records found for this ID "));

        repository.delete (entity);

    }
    private void addHateoasLinks(BookDTO dto) {
        dto.add(linkTo(methodOn(BookController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(BookController.class).findAll(1, 12, "asc")).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(BookController.class).create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(BookController.class).update(dto)).withRel("update").withType("PUT"));
        dto.add(linkTo(methodOn(BookController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
    }
}
