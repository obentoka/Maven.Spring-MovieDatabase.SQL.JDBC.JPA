package io.zipcoder.persistenceapp.controllers;

import io.zipcoder.persistenceapp.models.Person;
import io.zipcoder.persistenceapp.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService){
        this.personService = personService;
    }

    @GetMapping("/people/{id}")
    public ResponseEntity<Person> findById(@PathVariable Long id){
        return new ResponseEntity<>(personService.findPersonById(id), HttpStatus.OK);
    }

    @GetMapping("/people/")
    public ResponseEntity<Iterable<Person>> findAll(){
        return new ResponseEntity<>(personService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/people/")
    public ResponseEntity<Person> create(@RequestBody Person person){
        return new ResponseEntity<>(personService.createPerson(person), HttpStatus.CREATED);
    }

    @PutMapping("/people/{id}")
    public ResponseEntity<Person> udpate(@PathVariable Long id, @RequestBody Person person){
        return new ResponseEntity<>(personService.updatePerson(id, person), HttpStatus.OK);
    }

    @DeleteMapping("/people/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id){
        return new ResponseEntity<>(personService.deletePerson(id), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/people/all")
    public ResponseEntity<Boolean> deleteAll(@RequestBody Iterable<Person> personList){
        return new ResponseEntity<>(personService.deleteAll(personList), HttpStatus.NOT_FOUND);
    }

    @GetMapping("people/firstname/{firstName}")
    public ResponseEntity<Iterable<Person>> findByFirstName(@PathVariable String firstName){
        return new ResponseEntity<>(personService.findAllByFirstName(firstName), HttpStatus.OK);
    }

    @GetMapping("people/surname/{lastName}")
    public ResponseEntity<Iterable<Person>> findByLastName(@PathVariable String lastname){
        return new ResponseEntity<>(personService.findAllByLastName(lastname), HttpStatus.OK);
    }

    @GetMapping("people/birthday/{dob}")
    public ResponseEntity<Iterable<Person>> findByDOB(@PathVariable String birthday){
        return new ResponseEntity<>(personService.findAllByDOB(birthday), HttpStatus.OK);
    }

    @GetMapping("people/reverselookup/{mobile}")
    public ResponseEntity<Iterable<Person>> findByMobile(@PathVariable String mobile){
        return new ResponseEntity<>(personService.findAllByMobile(mobile), HttpStatus.OK);
    }

    @GetMapping("people/homeid/{homeid}")
    public ResponseEntity<Iterable<Person>> findByMobile(@PathVariable Long homid){
        return new ResponseEntity<>(personService.findAllByHomeId(homid), HttpStatus.OK);
    }

    @GetMapping("/people/surname/")
    public ResponseEntity<Map<String, Iterable<Person>>> getSurNameMap(){
        return new ResponseEntity<>(personService.mapLastName(), HttpStatus.OK);
    }

    @GetMapping("/people/firstname/")
    public ResponseEntity<Map<String, Iterable<Person>>> getFirstNameMap(){
        return new ResponseEntity<>(personService.mapFirstName(), HttpStatus.OK);
    }

    @GetMapping("/people/surname/stats")
    public ResponseEntity<Map<String, Integer>> getLastNameReport(){
        return new ResponseEntity<>(personService.getSurNameReport(), HttpStatus.OK);
    }

    @GetMapping("/people/firstname/stats")
    public ResponseEntity<Map<String, Integer>> getFirstNameReport(){
        return new ResponseEntity<>(personService.getFirstNameReport(), HttpStatus.OK);
    }
}
