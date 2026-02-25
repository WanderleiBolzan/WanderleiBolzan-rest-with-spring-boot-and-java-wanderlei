package br.com.wanderlei.services;

import br.com.wanderlei.data.dto.PersonDTO;
import br.com.wanderlei.exception.ResourceNotFoundException;
import static br.com.wanderlei.mapper.ObjectMapper.parseListObject;
import static br.com.wanderlei.mapper.ObjectMapper.parseObject;
import br.com.wanderlei.model.Person;
import br.com.wanderlei.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;


@Service
public class PersonServices {

    private final AtomicLong  counter = new AtomicLong();
    private Logger logger = LoggerFactory.getLogger(PersonServices.class.getName ());
    @Autowired
    PersonRepository repository;

    public List<PersonDTO> findAll() {

        logger.info("Finding all People");
        return parseListObject (repository.findAll (), PersonDTO.class);

    }


    public PersonDTO findById(Long id) {
        logger.info("Finding one PersonDTO");

        var entity =  repository.findById (id)
                .orElseThrow (() -> new ResourceNotFoundException("No Records found for this ID "));
        return parseObject(entity, PersonDTO.class);
    }

    public PersonDTO create(PersonDTO person) {
        logger.info("Creating one PersonDTO");

        var entity = parseObject(person, Person.class);

        return parseObject(repository.save(entity), PersonDTO.class);
    }

    public PersonDTO update(Person person) {
        logger.info("updating one PersonDTO");
        Person entity =  repository.findById (person.getId ( ))
                .orElseThrow (() -> new ResourceNotFoundException("No Records found for this ID "));

        entity.setFirstName (person.getFirstName ());
        entity.setLastName (person.getLastName ());
        entity.setAddress (person.getAddress ());
        entity.setGender (person.getGender ());

        return parseObject(repository.save(entity), PersonDTO.class);
    }

    public void delete(Long id) {
        logger.info("deleting one PersonDTO");

        Person entity =  repository.findById (id)
                .orElseThrow (() -> new ResourceNotFoundException("No Records found for this ID "));

        repository.delete (entity);

    }

}
