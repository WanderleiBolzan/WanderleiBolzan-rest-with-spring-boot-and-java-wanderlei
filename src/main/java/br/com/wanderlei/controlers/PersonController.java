package br.com.wanderlei.controlers;

import br.com.wanderlei.services.PersonServices;
import br.com.wanderlei.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonServices services;

    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Person findById(@PathVariable("id") Long id) {

        return services.findById (id);

    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Person> findAll() {

        return services.findAll();

    }
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Person create(@RequestBody Person person) {

        return services.create(person);

    }
    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Person update(@RequestBody Person person) {

        return services.update (person);

    }

    @DeleteMapping(value = "/{id}"
    )
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {

        services.delete(id);
        return ResponseEntity.noContent ().build ();

    }


}

