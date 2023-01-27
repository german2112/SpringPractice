package com.germant.springPractice;

import com.germant.springPractice.repos.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
    @Slf4j
    @Component
    public class AppCommandRunner implements CommandLineRunner {

        @Autowired
        private PersonRepository personRepository;

        @Transactional
        @Override
        public void run(String... args) throws Exception {
            log.info("Persons:");
            personRepository.findAll()
                    .forEach(c -> log.info(c.toString()));
        }

    }
