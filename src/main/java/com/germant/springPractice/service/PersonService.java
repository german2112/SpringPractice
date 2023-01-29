package com.germant.springPractice.service;

import com.germant.springPractice.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {

    public Person getPerson(Long id);

    public Person addPerson(String name, Long id);

    public Person updatePerson(Long id, String name);

    public List<Person> getAllPersons();

    public Person deleteById(Long id);
}
