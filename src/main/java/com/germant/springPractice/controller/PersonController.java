package com.germant.springPractice.controller;

import com.germant.springPractice.dto.PersonRegistrationDTO;
import com.germant.springPractice.model.Person;
import com.germant.springPractice.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@CacheConfig(cacheNames = "persons")
public class PersonController implements UserDetails {
    public static final String ALL_PERSONS = "allPersons";

    @Autowired
    PersonService personService;

    @Autowired
    CacheManager cacheManager;

    @GetMapping("/getById/{userId}")
    @Cacheable(key = "#userId")
    public Person getById(@PathVariable Integer userId) {
        return personService.getPerson(Long.valueOf(userId));
    }

    @GetMapping("/findAll")
    @Cacheable
    public List<Person> findAll() {
        cacheManager.getCache("persons");
         return personService.getAllPersons();
    }

    @PostMapping("/save")
    @CacheEvict(allEntries = true)
    public Person save(@RequestBody PersonRegistrationDTO personRegistrationDTO) {
        return personService.addPerson(personRegistrationDTO);
    }

    @CacheEvict(allEntries = true)
    @PostMapping("/update/{userId}/{name}")
    public Person update(@PathVariable Integer userId, @PathVariable String name) {
        return personService.updatePerson(Long.valueOf(userId), name);
    }

    @DeleteMapping("/delete/{userId}")
    @CacheEvict(allEntries = true)
    public Person delete(@PathVariable Integer userId) {
        return personService.deleteById(Long.valueOf(userId));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
