package com.germant.springPractice.service;

import com.germant.springPractice.dto.PersonRegistrationDTO;
import com.germant.springPractice.exception.NoSuchPersonExistException;
import com.germant.springPractice.exception.PersonAlreadyExistException;
import com.germant.springPractice.model.Person;
import com.germant.springPractice.model.Role;
import com.germant.springPractice.repos.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    PersonRepository personRepository;

    @Override
    public Person getPerson(Long userId) {
        return personRepository.findById(userId).orElseThrow(() ->
                new PersonAlreadyExistException("Person with id = " + userId +" does not exist"));
    }

    @Override
    @Cacheable("persons")
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @Override
    public Person addPerson(PersonRegistrationDTO personRegistrationDTO) {
        Person existingPerson = personRepository.findByName(personRegistrationDTO.getName()).orElse(null);
        if(existingPerson == null) {
            Person newPerson = new Person(personRegistrationDTO.getName()
                    , personRegistrationDTO.getPassword(),
                    List.of(new Role("USER_ROLE")));
            return personRepository.save(newPerson);
        }else{
            throw new PersonAlreadyExistException("The person with name = " + personRegistrationDTO.getName() + " already exists");
        }
    }

    @Override
    public Person updatePerson(Long id, String name) {
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
