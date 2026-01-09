package br.com.wanderlei.repository;

import br.com.wanderlei.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
