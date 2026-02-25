package br.com.wanderlei.mapper.custom;

import br.com.wanderlei.data.dto.v2.PersonDTOV2;
import br.com.wanderlei.model.Person;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersomMapper {

    public PersonDTOV2 convertEntityToDTO(Person person) {
        PersonDTOV2 dto = new PersonDTOV2 ();

        dto.setId(person.getId());
        dto.setFirstName (person.getFirstName ());
        dto.setLastName (person.getLastName ());
        dto.setBurthDate (new Date ());
        dto.setAddress (person.getAddress ());
        dto.setGender (person.getGender ());

        return dto;
    }

    public Person convertDTOToEntity(PersonDTOV2 person) {
        Person dto = new Person ();

        dto.setId(person.getId());
        dto.setFirstName (person.getFirstName ());
        dto.setLastName (person.getLastName ());
        dto.setAddress (person.getAddress ());
        dto.setGender (person.getGender ());

        return dto;
    }


}
