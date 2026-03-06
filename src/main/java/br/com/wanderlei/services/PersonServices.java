package br.com.wanderlei.services;

import br.com.wanderlei.controlers.PersonController;
import br.com.wanderlei.data.dto.PersonDTO;
import br.com.wanderlei.exception.RequiredObjectIsNullException;
import br.com.wanderlei.exception.ResourceNotFoundException;
import br.com.wanderlei.mapper.custom.PersomMapper;
import br.com.wanderlei.model.Person;
import br.com.wanderlei.repository.PersonRepository;
import jakarta.transaction.Transactional;
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

import static br.com.wanderlei.mapper.ObjectMapper.parseObject;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Service
public class PersonServices {
    private Logger logger = LoggerFactory.getLogger(PersonServices.class.getName ());
    @Autowired
    PersonRepository repository;
    @Autowired
    PagedResourcesAssembler<PersonDTO> assembler;

    @Autowired
    PersomMapper converter;

    public PagedModel<EntityModel<PersonDTO>> findAll(Pageable pageable) {

        logger.info("Finding all People");

        var people = repository.findAll(pageable);

        var peopleWithLinks =  people.map(person ->  {
            var dto = parseObject(person, PersonDTO.class);
            addHateoasLinks(dto);
            return dto;
        });

        Link findAllLink = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(PersonController.class)
                        .findAll(pageable.getPageNumber (),
                                pageable.getPageSize(),
                                String.valueOf (pageable.getSort())))
                                        .withSelfRel();
        return assembler.toModel (peopleWithLinks,
                findAllLink);
    }
    public PagedModel<EntityModel<PersonDTO>> findByName(String firstName, Pageable pageable) {

        logger.info("Finding People by name!");

        var people = repository.findPeopleByName(firstName, pageable);

        var peopleWithLinks = people.map(person -> {
            var dto = parseObject(person, PersonDTO.class);
            addHateoasLinks(dto);
            return dto;
        });

        Link findAllLink = WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(PersonController.class)
                                .findAll(
                                        pageable.getPageNumber(),
                                        pageable.getPageSize(),
                                        String.valueOf(pageable.getSort())))
                .withSelfRel();
        return assembler.toModel(peopleWithLinks, findAllLink);
    }


    public PersonDTO findById(Long id) {
        logger.info("Finding one PersonDTO");

        var entity =  repository.findById (id)
                .orElseThrow (() -> new ResourceNotFoundException("No Records found for this ID "));

        var dto = parseObject(entity, PersonDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public PersonDTO create(PersonDTO person) {

        if (person == null) throw new RequiredObjectIsNullException ();

        logger.info("Creating one PersonDTO");

        var entity = parseObject(person, Person.class);

        var dto = parseObject(repository.save(entity), PersonDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public PersonDTO update(PersonDTO person) {


        if (person == null) throw new RequiredObjectIsNullException ();

        logger.info("updating one PersonDTO");
        Person entity =  repository.findById (person.getId ( ))
                .orElseThrow (() -> new ResourceNotFoundException("No Records found for this ID "));

        entity.setFirstName (person.getFirstName ());
        entity.setLastName (person.getLastName ());
        entity.setAddress (person.getAddress ());
        entity.setGender (person.getGender ());

        var dto =  parseObject(repository.save(entity), PersonDTO.class);
        addHateoasLinks(dto);
        return dto;
    }
    @Transactional
    public PersonDTO disablePerson(Long id) {
        logger.info("disabling one Person");

        repository.findById (id)
             .orElseThrow (() -> new ResourceNotFoundException("No Records found for this ID "));

        repository.disablePerson (id);

        var entity = repository.findById (id).get();
        var dto =  parseObject(entity, PersonDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public void delete(Long id) {
        logger.info("deleting one PersonDTO");

        Person entity =  repository.findById (id)
                .orElseThrow (() -> new ResourceNotFoundException("No Records found for this ID "));

        repository.delete (entity);

    }
    private void addHateoasLinks(PersonDTO dto) {
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).findAll(1, 12, "asc")).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(PersonController.class).update(dto)).withRel("update").withType("PUT"));
        dto.add(linkTo(methodOn(PersonController.class).disablePerson(dto.getId ( ))).withRel("disable").withType("PATCH"));
        dto.add(linkTo(methodOn(PersonController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
    }

}
