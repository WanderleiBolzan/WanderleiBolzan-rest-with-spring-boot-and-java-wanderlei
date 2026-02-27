package br.com.wanderlei.controlers;

import br.com.wanderlei.data.dto.v1.PersonDTO;
import br.com.wanderlei.data.dto.v2.PersonDTOV2;

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
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }
    )
    public PersonDTO findById(@PathVariable("id") Long id) {

        return services.findById (id);

    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public List<PersonDTO> findAll() {

        return services.findAll();

    }
    @PutMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }
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

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }
    )

    public PersonDTO create(@RequestBody PersonDTO person) {

        return services.create(person);

    }

    @PostMapping(value = "/v2",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }
    )

    public PersonDTOV2 create(@RequestBody PersonDTOV2 person) {

        return services.createV2(person);

    }


}

