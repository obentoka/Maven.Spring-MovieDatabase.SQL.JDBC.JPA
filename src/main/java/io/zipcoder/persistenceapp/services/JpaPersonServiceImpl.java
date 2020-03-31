package io.zipcoder.persistenceapp.services;

import io.zipcoder.persistenceapp.models.Person;
import io.zipcoder.persistenceapp.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@Primary
public class JpaPersonServiceImpl implements PersonService{

    private PersonRepository repository;

    @Autowired
    public JpaPersonServiceImpl(PersonRepository repository){
        this.repository = repository;
    }

    public Person findPersonById(Long id) {
        return repository.findOne(id);
    }

    public Iterable<Person> findAll() {
        return repository.findAll();
    }

    public Person createPerson(Person person) {
        person.setId(repository.count()+1L);
        return repository.save(person);
    }

    public Person updatePerson(Long id, Person newPersondata) {
        if(repository.exists(id)) {
            Person originalPerson = repository.findOne(id);
            originalPerson.setFirstName(newPersondata.getFirstName());
            originalPerson.setLastName(newPersondata.getLastName());
            originalPerson.setMobile(newPersondata.getMobile());
            originalPerson.setBirthday(newPersondata.getBirthday());
            originalPerson.setHomeId(newPersondata.getHomeId());
            return repository.save(originalPerson);
        }
        else
            return createPerson(newPersondata);
    }

    public Boolean deletePerson(Long id) {
        if(repository.exists(id)) {
            repository.delete(id);
            return true;
        }else
            return false;
    }

    public Boolean deleteAll(Iterable<Person> personList) {
        Boolean retBool = false;
        for(Person p : personList) {
            retBool = deletePerson(p.getId());
        }
        return retBool;
    }

    public Iterable<Person> findAllByFirstName(String firstName) {
        return repository.findAllByFirstName(firstName);
    }

    public Iterable<Person> findAllByLastName(String lastName) {
        return repository.findAllByLastName(lastName);
    }

    public Iterable<Person> findAllByDOB(String dob) {
        return repository.findAllByBirthday(dob);
    }

    public Iterable<Person> findAllByMobile(String moible) {
        return repository.findAllByMobile(moible);
    }

    public Iterable<Person> findAllByHomeId(Long homeid) {
        return repository.findAllByHomeId(homeid);
    }

    public List<String> getAllSurNames() {
        return repository.findDistinctLastName();
    }

    public List<String> getAllFirstNames() {
        return repository.findDistinctFirstName();
    }

    public Map<String, Iterable<Person>> mapLastName() {
        Map<String, Iterable<Person>> retMap = new LinkedHashMap<>();
        List<String> lastName = getAllSurNames();
        for(String lName : lastName){
            retMap.put(lName, findAllByLastName(lName));
        }
        return retMap;
    }

    public Map<String, Iterable<Person>> mapFirstName() {
        Map<String, Iterable<Person>> retMap = new LinkedHashMap<>();
        List<String> firstName = getAllFirstNames();
        for(String fName : firstName){
            retMap.put(fName, findAllByFirstName(fName));
        }
        return retMap;
    }

    public Map<String, Integer> getSurNameReport() {
        Map<String, Integer> retMap = new LinkedHashMap<>();
        List<String> lastName = getAllSurNames();
        for(String lName : lastName){
            retMap.put(lName, repository.countPeopleByLastName(lName));
        }
        return retMap;
    }

    public Map<String, Integer> getFirstNameReport() {
        Map<String, Integer> retMap = new LinkedHashMap<>();
        List<String> firstName = getAllFirstNames();
        for(String fName : firstName){
            retMap.put(fName, repository.countPeopleByFirstName(fName));
        }
        return retMap;
    }
}
