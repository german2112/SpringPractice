package com.germant.springPractice.controller;

import com.germant.springPractice.model.Person;
import com.germant.springPractice.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
public class PersonController implements UserDetails {
    @Autowired
    PersonService personService;
    @GetMapping("/getById/{userId}")
    @Cacheable(value = "persons", key="#userId")
    public Person getById(@PathVariable Long userId) {
        return personService.getPerson(Long.valueOf(userId));
    }

    @GetMapping("/findAll")
    @Cacheable("persons")
    public List<Person> findAll() {
         return personService.getAllPersons();
    }

    @PostMapping("/save/{name}/{id}")
    public Person save(@PathVariable String name, @PathVariable Integer id) {
        return personService.addPerson(name, id);
    }

    @PostMapping("/update/{id}/{name}")
    public Person update(@PathVariable Integer id, @PathVariable String name) {
        return personService.updatePerson(id, name);
    }

    @DeleteMapping("/delete/{id}")
    @CacheEvict(value = "persons", key= "#id")
    public String delete(@PathVariable Integer id) {
        return personService.deleteById(Long.valueOf(id));
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
