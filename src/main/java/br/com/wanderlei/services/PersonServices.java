package br.com.wanderlei.services;

import br.com.wanderlei.exception.ResourceNotFoundException;
import br.com.wanderlei.model.Person;
import br.com.wanderlei.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private final AtomicLong  counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName ());
    @Autowired
    PersonRepository repository;

    public List<Person> findAll() {

        logger.info("Finding all People");
        return repository.findAll ();

    }


    public Person findById(Long id) {
        logger.info("Finding one Person");

        return repository.findById (id)
                .orElseThrow (() -> new ResourceNotFoundException("No Records found for this ID "));

    }

    public Person create(Person person) {
        logger.info("Creating one Person");

        return repository.save (person);
    }

    public Person update(Person person) {
        logger.info("updating one Person");
        Person entity =  repository.findById (person.getId ( ))
                .orElseThrow (() -> new ResourceNotFoundException("No Records found for this ID "));

        entity.setFirstName (person.getFirstName ());
        entity.setLastName (person.getLastName ());
        entity.setAddress (person.getAddress ());
        entity.setGender (person.getGender ());


        return repository.save (entity);
    }

    public void delete(Long id) {
        logger.info("deleting one Person");

        Person entity =  repository.findById (id)
                .orElseThrow (() -> new ResourceNotFoundException("No Records found for this ID "));

        repository.delete (entity);

    }

}
