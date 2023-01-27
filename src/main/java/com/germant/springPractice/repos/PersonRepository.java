package com.germant.springPractice.repos;

import com.germant.springPractice.model.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person, Long> {
    List<Person> findAll();

    Optional<Person> findById(Integer aLong);

    Person save(Person person);

    void deleteById(Long id);
}
