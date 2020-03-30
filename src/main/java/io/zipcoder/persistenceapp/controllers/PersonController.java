package io.zipcoder.persistenceapp.controllers;

import io.zipcoder.persistenceapp.models.Person;
import io.zipcoder.persistenceapp.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("people/firstname/{firstName}")
    public ResponseEntity<Iterable<Person>> findByFirstName(@PathVariable String firstName){
        return new ResponseEntity<>(personService.findAllByFirstName(firstName), HttpStatus.OK);
    }

    @GetMapping("people/lastname/{lastName}")
    public ResponseEntity<Iterable<Person>> findByLastName(@PathVariable String lastname){
        return new ResponseEntity<>(personService.findAllByLastName(lastname), HttpStatus.OK);
    }

    @GetMapping("people/birthday/{dob}")
    public ResponseEntity<Iterable<Person>> findByDOB(@PathVariable String birthday){
        return new ResponseEntity<>(personService.findAllByDOB(birthday), HttpStatus.OK);
    }

    @GetMapping("people/mobile/{mobile}")
    public ResponseEntity<Iterable<Person>> findByMobile(@PathVariable String mobile){
        return new ResponseEntity<>(personService.findAllByMobile(mobile), HttpStatus.OK);
    }

    @GetMapping("people/homeid/{homeid}")
    public ResponseEntity<Iterable<Person>> findByMobile(@PathVariable Long homid){
        return new ResponseEntity<>(personService.findAllByHomeId(homid), HttpStatus.OK);
    }
}
