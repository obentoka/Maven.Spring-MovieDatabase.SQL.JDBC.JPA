package io.zipcoder.persistenceapp.services;

import io.zipcoder.persistenceapp.models.Person;

import java.util.List;
import java.util.Map;

public interface PersonService {

    Person findPersonById(Long id);

    Iterable<Person> findAll();

    Person createPerson(Person person);

    Person updatePerson(Long id, Person person);

    Boolean deletePerson(Long id);

    Boolean deleteAll(Iterable<Person> personList);

    Iterable<Person> findAllByFirstName(String firstName);

    Iterable<Person> findAllByLastName(String lastName);

    Iterable<Person> findAllByDOB(String dob);

    Iterable<Person> findAllByMobile(String moible);

    Iterable<Person> findAllByHomeId(Long homeid);

    List<String> getAllSurNames();

    List<String> getAllFirstNames();

    Map<String, Iterable<Person>> mapLastName();

    Map<String, Iterable<Person>> mapFirstName();

    Map<String, Integer> getSurNameReport();

    Map<String, Integer> getFirstNameReport();
}
