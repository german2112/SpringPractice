package com.germant.springPractice.service;

import com.germant.springPractice.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {

    public Person getPerson(Long id);

    public Person addPerson(String name, Integer id);

    public Person updatePerson(Integer id, String name);

    public List<Person> getAllPersons();

    public String deleteById(Long id);
}
