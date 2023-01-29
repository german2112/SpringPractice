package com.germant.springPractice.service;

import com.germant.springPractice.exception.NoSuchPersonExistException;
import com.germant.springPractice.exception.PersonAlreadyExistException;
import com.germant.springPractice.model.Person;
import com.germant.springPractice.repos.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    PersonRepository personRepository;

    @Override
    public Person getPerson(@PathVariable Long userId) {
        return personRepository.findById(userId).orElseThrow(() ->
                new PersonAlreadyExistException("Person with id = " + userId +" does not exist"));
    }

    @Override
    @Cacheable("persons")
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @Override
    public Person addPerson(@PathVariable String name, @PathVariable Long id) {
        Person existingPerson = personRepository.findById(id).orElse(null);
        if(existingPerson == null) {
            Person newPerson = new Person(id ,name);
            return personRepository.save(newPerson);
        }else{
            throw new PersonAlreadyExistException("The person with id = " + id + " already exists");
        }
    }

    @Override
    public Person updatePerson(@PathVariable Long id, @PathVariable String name) {
        Person existingPerson = personRepository.findById(id).orElse(null);
        if(existingPerson == null) {
            throw new NoSuchPersonExistException("Person with id = " + id + " does not exist");
        } else {
            existingPerson.setId(id);
            existingPerson.setName(name);
            return personRepository.save(existingPerson);
        }
    }

    @Override
    public Person deleteById(Long id) {
        Person existingPerson = personRepository.findById(id).orElse(null);
        if(existingPerson == null) {
            throw new NoSuchPersonExistException("Person with id = " + id + " does not exist");
        } else {
            personRepository.deleteById(id);
            return existingPerson;
        }
    }
}
