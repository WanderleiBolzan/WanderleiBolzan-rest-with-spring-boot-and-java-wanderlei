package br.com.wanderlei.controlers;

import br.com.wanderlei.data.dto.PersonDTO;
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
    public PersonDTO findById(@PathVariable("id") Long id) {

        return services.findById (id);

    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PersonDTO> findAll() {

        return services.findAll();

    }
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public PersonDTO create(@RequestBody PersonDTO person) {

        return services.create(person);

    }
    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public PersonDTO update(@RequestBody Person person) {

        return services.update (person);

    }

    @DeleteMapping(value = "/{id}"
    )
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {

        services.delete(id);
        return ResponseEntity.noContent ().build ();

    }


}

