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

@RestController
@RequestMapping("/api/person/v1")
@Tag(name = "People", description = "Endpoint for management People")
public class PersonController implements PersonControllerDocs {

    @Autowired
    private PersonServices services;

    @Override
    @GetMapping(value = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public PersonDTO findById(@PathVariable(value = "id") Long id) {
        return services.findById(id);
    }

    @Override
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<PersonDTO> findAll() {
        return services.findAll();
    }

    @Override
    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public PersonDTO create(@RequestBody PersonDTO person) {
        return services.create(person);
    }

    @Override
    @PutMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public PersonDTO update(@RequestBody PersonDTO person) {
        return services.update(person);
    }

    // CORREÇÃO AQUI: Adicionado o value = "/{id}" para que o @PathVariable funcione
    @Override
    @PatchMapping(value = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public PersonDTO disablePerson(@PathVariable(value = "id") Long id) {
        return services.disablePerson(id);
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        services.delete(id);
        return ResponseEntity.noContent().build();
    }
}