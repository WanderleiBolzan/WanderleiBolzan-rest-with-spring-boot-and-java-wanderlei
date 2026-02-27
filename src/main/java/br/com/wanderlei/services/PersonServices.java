package br.com.wanderlei.services;

import br.com.wanderlei.controlers.PersonController;
import br.com.wanderlei.data.dto.PersonDTO;
import br.com.wanderlei.exception.RequiredObjectIsNullException;
import br.com.wanderlei.exception.ResourceNotFoundException;
import static br.com.wanderlei.mapper.ObjectMapper.parseListObject;
import static br.com.wanderlei.mapper.ObjectMapper.parseObject;

import br.com.wanderlei.mapper.custom.PersomMapper;
import br.com.wanderlei.model.Person;
import br.com.wanderlei.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;


@Service
public class PersonServices {

    private final AtomicLong  counter = new AtomicLong();
    private Logger logger = LoggerFactory.getLogger(PersonServices.class.getName ());
    @Autowired
    PersonRepository repository;

    @Autowired
    PersomMapper converter;


    public List<PersonDTO> findAll() {

        logger.info("Finding all People");
        var persons =  parseListObject (repository.findAll (), PersonDTO.class);
        persons.forEach (this::addHateoasLinks);
        return persons;
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

    public void delete(Long id) {
        logger.info("deleting one PersonDTO");

        Person entity =  repository.findById (id)
                .orElseThrow (() -> new ResourceNotFoundException("No Records found for this ID "));

        repository.delete (entity);

    }
    private void addHateoasLinks(PersonDTO dto) {
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).findAll()).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(PersonController.class).update(dto)).withRel("update").withType("PUT"));
        dto.add(linkTo(methodOn(PersonController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
    }

}
