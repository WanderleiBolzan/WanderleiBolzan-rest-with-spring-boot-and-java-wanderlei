package br.com.wanderlei.controlers;

import br.com.wanderlei.controlers.docs.PersonControllerDocs;
import br.com.wanderlei.data.dto.PersonDTO;
import br.com.wanderlei.services.PersonServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//@CrossOrigin(origins="http://localhost:8082")
@RestController
@RequestMapping("/api/person/v1")
@Tag(name="People", description = "Endpoint for management People")

public class PersonController implements PersonControllerDocs {

    @Autowired
    private PersonServices services;

    @Override
    @GetMapping(value = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }
    )

    public PersonDTO findById(@PathVariable ( "id" ) Long id) {

        return services.findById (id);

    }

    @Override
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE })

    public List<PersonDTO> findAll() {

        return services.findAll();

    }
    @Override
    @PutMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }
    )


    public PersonDTO update(@RequestBody PersonDTO person) {

        return services.update (person);

    }
    @Override
    @DeleteMapping(value = "/{id}"
    )
    public ResponseEntity<?> delete(@PathVariable ( "id" ) Long id) {

        services.delete(id);
        return ResponseEntity.noContent ().build ();

    }

    @Override
    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }
    )


    public PersonDTO create(@RequestBody PersonDTO person) {

        return services.create(person);

    }

}

