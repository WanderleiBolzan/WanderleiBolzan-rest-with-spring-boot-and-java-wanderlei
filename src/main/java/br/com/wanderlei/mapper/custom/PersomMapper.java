package br.com.wanderlei.mapper.custom;

import br.com.wanderlei.data.dto.PersonDTO;
import br.com.wanderlei.model.Person;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersomMapper {

    public PersonDTO convertEntityToDTO(Person person) {
        PersonDTO dto = new PersonDTO ();

        dto.setId(person.getId());
        dto.setFirstName (person.getFirstName ());
        dto.setLastName (person.getLastName ());
        dto.setAddress (person.getAddress ());
        dto.setGender (person.getGender ());

        return dto;
    }

    public Person convertDTOToEntity(PersonDTO person) {
        Person dto = new Person ();

        dto.setId(person.getId());
        dto.setFirstName (person.getFirstName ());
        dto.setLastName (person.getLastName ());
        dto.setAddress (person.getAddress ());
        dto.setGender (person.getGender ());

        return dto;
    }


}
