package io.zipcoder.persistenceapp.services;

import io.zipcoder.persistenceapp.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    JdbcTemplate jdbcTemplate;


}
