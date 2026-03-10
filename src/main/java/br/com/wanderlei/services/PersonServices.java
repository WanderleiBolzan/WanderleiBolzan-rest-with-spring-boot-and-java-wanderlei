package br.com.wanderlei.services;

import br.com.wanderlei.controlers.PersonController;
import br.com.wanderlei.data.dto.PersonDTO;
import br.com.wanderlei.exception.BadRequestException;
import br.com.wanderlei.exception.FileStorageException;
import br.com.wanderlei.exception.RequiredObjectIsNullException;
import br.com.wanderlei.exception.ResourceNotFoundException;
import br.com.wanderlei.file.importer.contract.FileImporter;
import br.com.wanderlei.file.importer.factory.FileImporterFactory;
import br.com.wanderlei.mapper.custom.PersomMapper;
import br.com.wanderlei.model.Person;
import br.com.wanderlei.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import org.springframework.web.multipart.MultipartFile;


import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import static br.com.wanderlei.mapper.ObjectMapper.parseObject;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PersonServices {

    private final Logger logger = LoggerFactory.getLogger(PersonServices.class.getName());

    @Autowired
    private PersonRepository repository;

    @Autowired
    private FileImporterFactory importerFactory;

    @Autowired
    private PagedResourcesAssembler<PersonDTO> assembler;

    @Autowired
    private PersomMapper converter;

    public PagedModel<EntityModel<PersonDTO>> findAll(Pageable pageable) {
        logger.info("Finding all People");
        var people = repository.findAll(pageable);
        return buildPageModel(pageable, people);
    }

    public PagedModel<EntityModel<PersonDTO>> findByName(String firstName, Pageable pageable) {
        logger.info("Finding People by name!");
        var people = repository.findPeopleByName(firstName, pageable);
        return buildPageModel(pageable, people);
    }

    public PersonDTO findById(Long id) {
        logger.info("Finding one PersonDTO");
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No Records found for this ID"));

        var dto = parseObject(entity, PersonDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public PersonDTO create(PersonDTO person) {
        if (person == null) throw new RequiredObjectIsNullException();
        logger.info("Creating one PersonDTO");

        var entity = parseObject(person, Person.class);
        var dto = parseObject(repository.save(entity), PersonDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    @Transactional
    public List<PersonDTO> massCreation(MultipartFile file) {
        logger.info("Importing people from file: {}", file.getOriginalFilename());

        if (file == null || file.isEmpty()) {
            throw new BadRequestException("Please upload a valid file.");
        }

        try (InputStream inputStream = file.getInputStream()) {
            String fileName = Optional.ofNullable(file.getOriginalFilename())
                    .orElseThrow(() -> new BadRequestException("File name cannot be null"));

            // Obtém a estratégia de importação (CSV, XML, etc)
            FileImporter fileImporter = this.importerFactory.getImporter(fileName);

            // Processa o arquivo, salva no banco e retorna a lista de entidades
            List<Person> entities = fileImporter.importFile(inputStream).stream()
                    .map(dto -> {
                        var entity = parseObject(dto, Person.class);
                        return repository.save(entity);
                    })
                    .toList();

            // Converte as entidades salvas para DTO e adiciona HATEOAS
            return entities.stream()
                    .map(entity -> {
                        var dto = parseObject(entity, PersonDTO.class);
                        addHateoasLinks(dto);
                        return dto;
                    }).toList();

        } catch (BadRequestException e) {
            throw e; // Repassa erros de validação
        } catch (Exception e) {
            logger.error("Error processing mass creation file: ", e);
            throw new FileStorageException("Error processing the file: " + e.getMessage());
        }
    }

    public PersonDTO update(PersonDTO person) {
        if (person == null) throw new RequiredObjectIsNullException();
        logger.info("Updating one PersonDTO");

        Person entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No Records found for this ID"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var dto = parseObject(repository.save(entity), PersonDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    @Transactional
    public PersonDTO disablePerson(Long id) {
        logger.info("Disabling one Person");

        repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No Records found for this ID"));

        repository.disablePerson(id);

        var entity = repository.findById(id).get();
        var dto = parseObject(entity, PersonDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public void delete(Long id) {
        logger.info("Deleting one PersonDTO");
        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No Records found for this ID"));
        repository.delete(entity);
    }

    private PagedModel<EntityModel<PersonDTO>> buildPageModel(Pageable pageable, Page<Person> people) {
        var peopleWithLinks = people.map(person -> {
            var dto = parseObject(person, PersonDTO.class);
            addHateoasLinks(dto);
            return dto;
        });

        Link findAllLink = linkTo(methodOn(PersonController.class)
                .findAll(pageable.getPageNumber(),
                        pageable.getPageSize(),
                        pageable.getSort().toString())).withSelfRel();

        return assembler.toModel(peopleWithLinks, findAllLink);
    }

    private void addHateoasLinks(PersonDTO dto) {
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).findAll(1, 12, "asc")).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).findByName("", 1, 12, "asc")).withRel("findByName").withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(PersonController.class)).slash ("massCreation").withRel("massCreation").withType("POST"));
        dto.add(linkTo(methodOn(PersonController.class).update(dto)).withRel("update").withType("PUT"));
        dto.add(linkTo(methodOn(PersonController.class).disablePerson(dto.getId())).withRel("disable").withType("PATCH"));
        dto.add(linkTo(methodOn(PersonController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
    }
}