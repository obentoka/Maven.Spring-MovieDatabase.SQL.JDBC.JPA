package io.zipcoder.persistenceapp.services;

import io.zipcoder.persistenceapp.models.Person;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JdbcPersonServiceImplTest {

    PersonService service;
    Person testPerson;

    @Before
    public void init(){
//        service = new JdbcPersonServiceImpl();
        testPerson = new Person();
    }

    @Test
    public void findTest(){
        testPerson = new Person(1L, "Carbral", "Sheeri",
                "230-4233", "1970-02-23", 2L);

        Person actual = service.findPersonById(1L);

        assertEquals(testPerson.getBirthday(), actual.getBirthday());
        assertEquals(testPerson.getLastName(), actual.getLastName());
        assertEquals(testPerson.getFirstName(), actual.getFirstName());
        assertEquals(testPerson.getHomeId(), actual.getHomeId());
        assertEquals(testPerson.getMobile(), actual.getMobile());
    }

    @Test
    public void createTest(){
        testPerson = new Person("Jake", "Gillanhal",
                "230-4233", "1980-01-01", 1L);

        Person actual = service.createPerson(testPerson);

        assertEquals(testPerson.getBirthday(), actual.getBirthday());
        assertEquals(testPerson.getLastName(), actual.getLastName());
        assertEquals(testPerson.getFirstName(), actual.getFirstName());
        assertEquals(testPerson.getHomeId(), actual.getHomeId());
        assertEquals(testPerson.getMobile(), actual.getMobile());
    }

    @Test
    public void findAllTest(){
        List<Person> listPerson = new ArrayList<>();
        listPerson.add(new Person(1L, "Carbral", "Sheeri", "230-4233", "1970-02-23", 2L));
        listPerson.add(new Person(2L,"Sharam", "Raj", "186-5223", "1980-08-31", 3L));
        listPerson.add(new Person(3L,"Durand", "Noelle", "395-6161", "1960-07-06", 1L));
        listPerson.add(new Person(4L,"Smith", "Thomas", "395-6181", "1987-07-06", 1L));
        listPerson.add(new Person(5L, "Smith", "Jane", "393-6181", "1987-12-06", 3L));
        listPerson.add(new Person(6L, "Brown", "Doug", "466-6241", "1954-12-07", 3L));
        AtomicInteger i = new AtomicInteger(0);
        Stream.of(service.findAll())
            .map(Iterable::iterator)
            .forEachOrdered(x -> {
                if(x.hasNext()) {
                    Person currPerson = x.next();
                    assertEquals(currPerson.getLastName(), listPerson.get(i.get()).getLastName());
                    assertEquals(currPerson.getFirstName(), listPerson.get(i.get()).getFirstName());
                    assertEquals(currPerson.getMobile(), listPerson.get(i.get()).getMobile());
                    assertEquals(currPerson.getBirthday(), listPerson.get(i.get()).getBirthday());
                    assertEquals(currPerson.getHomeId(), listPerson.get(i.getAndIncrement()).getHomeId());
                }
            });
    }

    @Test
    public void updateTest(){
        Person change = service.updatePerson(1L, new Person("jake", "gil", "0000", "3000-01-01", 3L));
        Person actual = service.findPersonById(1L);

        assertEquals(change.getBirthday(), actual.getBirthday());
        assertEquals(change.getLastName(), actual.getLastName());
        assertEquals(change.getFirstName(), actual.getFirstName());
        assertEquals(change.getHomeId(), actual.getHomeId());
        assertEquals(change.getMobile(), actual.getMobile());
    }

    @Test
    public void updateTest2(){
        Person change = service.updatePerson(7L, new Person("jake", "gil", "0000", "3000-01-01", 3L));
        Person actual = service.findPersonById(7L);

        assertEquals(change.getBirthday(), actual.getBirthday());
        assertEquals(change.getLastName(), actual.getLastName());
        assertEquals(change.getFirstName(), actual.getFirstName());
        assertEquals(change.getHomeId(), actual.getHomeId());
        assertEquals(change.getMobile(), actual.getMobile());
    }

    @Test
    public void deleteTest(){
        assertFalse(service.deletePerson(7L));
    }

    @Test
    public void deleteTest2(){
        assertTrue(service.deletePerson(1L));
        assertNull(service.findPersonById(1L));
    }

    @Test
    public void deleteAllTest(){
        List<Person> listPerson = new ArrayList<>();
        listPerson.add(new Person(1L, "Carbral", "Sheeri", "230-4233", "1970-02-23", 2L));
        listPerson.add(new Person(2L,"Sharam", "Raj", "186-5223", "1980-08-31", 3L));
        listPerson.add(new Person(3L,"Durand", "Noelle", "395-6161", "1960-07-06", 1L));
        assertTrue(service.deleteAll(listPerson));
        assertNull(service.findPersonById(1L));
        assertNull(service.findPersonById(2L));
        assertNull(service.findPersonById(3L));
    }

    @Test
    public void deleteAllTest2(){
        List<Person> listPerson = new ArrayList<>();
        assertFalse(service.deleteAll(listPerson));
    }

    @Test
    public void findByFirstNameTest(){
        List<Person> listPerson = new ArrayList<>();
        listPerson.add(new Person(2L,"Sharam", "Raj", "186-5223", "1980-08-31", 3L));
        AtomicInteger i = new AtomicInteger(0);
        Stream.of(service.findAllByFirstName("Raj"))
                .map(Iterable::iterator)
                .forEachOrdered(x -> {
                    if(x.hasNext()) {
                        Person currPerson = x.next();
                        assertEquals(currPerson.getLastName(), listPerson.get(i.get()).getLastName());
                        assertEquals(currPerson.getFirstName(), listPerson.get(i.get()).getFirstName());
                        assertEquals(currPerson.getMobile(), listPerson.get(i.get()).getMobile());
                        assertEquals(currPerson.getBirthday(), listPerson.get(i.get()).getBirthday());
                        assertEquals(currPerson.getHomeId(), listPerson.get(i.getAndIncrement()).getHomeId());
                    }
                });
    }

    @Test
    public void findByLastNameTest(){
        List<Person> listPerson = new ArrayList<>();
        listPerson.add(new Person(4L,"Smith", "Thomas", "395-6181", "1987-07-06", 1L));
        listPerson.add(new Person(5L, "Smith", "Jane", "393-6181", "1987-12-06", 3L));
        AtomicInteger i = new AtomicInteger(0);
        Stream.of(service.findAllByLastName("Smith"))
                .map(Iterable::iterator)
                .forEachOrdered(x -> {
                    if(x.hasNext()) {
                        Person currPerson = x.next();
                        assertEquals(currPerson.getLastName(), listPerson.get(i.get()).getLastName());
                        assertEquals(currPerson.getFirstName(), listPerson.get(i.get()).getFirstName());
                        assertEquals(currPerson.getMobile(), listPerson.get(i.get()).getMobile());
                        assertEquals(currPerson.getBirthday(), listPerson.get(i.get()).getBirthday());
                        assertEquals(currPerson.getHomeId(), listPerson.get(i.getAndIncrement()).getHomeId());
                    }
                });
    }

    @Test
    public void findAllByDOBTest(){
        List<Person> listPerson = new ArrayList<>();
        listPerson.add(new Person(4L,"Smith", "Thomas", "395-6181", "1987-07-06", 1L));
        AtomicInteger i = new AtomicInteger(0);
        Stream.of(service.findAllByDOB("1987-07-06"))
                .map(Iterable::iterator)
                .forEachOrdered(x -> {
                    if(x.hasNext()) {
                        Person currPerson = x.next();
                        assertEquals(currPerson.getLastName(), listPerson.get(i.get()).getLastName());
                        assertEquals(currPerson.getFirstName(), listPerson.get(i.get()).getFirstName());
                        assertEquals(currPerson.getMobile(), listPerson.get(i.get()).getMobile());
                        assertEquals(currPerson.getBirthday(), listPerson.get(i.get()).getBirthday());
                        assertEquals(currPerson.getHomeId(), listPerson.get(i.getAndIncrement()).getHomeId());
                    }
                });
    }

    @Test
    public void findAllByMoibleTest(){
        List<Person> listPerson = new ArrayList<>();
        listPerson.add(new Person(5L, "Smith", "Jane", "393-6181", "1987-12-06", 3L));
        AtomicInteger i = new AtomicInteger(0);
        Stream.of(service.findAllByMobile("393-6181"))
                .map(Iterable::iterator)
                .forEachOrdered(x -> {
                    if(x.hasNext()) {
                        Person currPerson = x.next();
                        assertEquals(currPerson.getLastName(), listPerson.get(i.get()).getLastName());
                        assertEquals(currPerson.getFirstName(), listPerson.get(i.get()).getFirstName());
                        assertEquals(currPerson.getMobile(), listPerson.get(i.get()).getMobile());
                        assertEquals(currPerson.getBirthday(), listPerson.get(i.get()).getBirthday());
                        assertEquals(currPerson.getHomeId(), listPerson.get(i.getAndIncrement()).getHomeId());
                    }
                });
    }

    @Test
    public void findAllHomeIDTest(){
        List<Person> listPerson = new ArrayList<>();
        listPerson.add(new Person(2L,"Sharam", "Raj", "186-5223", "1980-08-31", 3L));
        listPerson.add(new Person(5L, "Smith", "Jane", "393-6181", "1987-12-06", 3L));
        listPerson.add(new Person(6L, "Brown", "Doug", "466-6241", "1954-12-07", 3L));
        AtomicInteger i = new AtomicInteger(0);
        Stream.of(service.findAllByHomeId(3L))
                .map(Iterable::iterator)
                .forEachOrdered(x -> {
                    if(x.hasNext()) {
                        Person currPerson = x.next();
                        assertEquals(currPerson.getLastName(), listPerson.get(i.get()).getLastName());
                        assertEquals(currPerson.getFirstName(), listPerson.get(i.get()).getFirstName());
                        assertEquals(currPerson.getMobile(), listPerson.get(i.get()).getMobile());
                        assertEquals(currPerson.getBirthday(), listPerson.get(i.get()).getBirthday());
                        assertEquals(currPerson.getHomeId(), listPerson.get(i.getAndIncrement()).getHomeId());
                    }
                });
    }

    @Test
    public void getAllSurNamesTest(){
        String[] lastNames = new String[]{"Brown", "Sharam", "Smith", "Durand", "Carbral"};
        List<String> lnAsList = new LinkedList<>(Arrays.asList(lastNames));
        Stream.of(service.getAllSurNames())
                .forEach(x -> assertTrue(lnAsList.contains(x.iterator().next())));
    }

    @Test
    public void getAllFirstNamesTest(){
        String[] firstNames = new String[]{"Sheeri", "Raj", "Noelle", "Thomas", "Jane", "Doug"};
        List<String> fnAsList = new LinkedList<>(Arrays.asList(firstNames));
        Stream.of(service.getAllFirstNames())
                .forEach(x -> assertTrue(fnAsList.contains(x.iterator().next())));
    }

    @Test
    public void mapLastNameTest(){
        Map<String, Iterable<Person>> actual = service.mapLastName();
        Person expected = new Person(3L,"Durand", "Noelle", "395-6161", "1960-07-06", 1L);
        for(Person p : actual.get("Durand")){
            assertEquals(expected.getBirthday(), p.getBirthday());
            assertEquals(expected.getLastName(), p.getLastName());
            assertEquals(expected.getFirstName(), p.getFirstName());
            assertEquals(expected.getHomeId(), p.getHomeId());
            assertEquals(expected.getMobile(), p.getMobile());
        }

    }

    @Test
    public void mapFirstNameTest(){
        Map<String, Iterable<Person>> actual = service.mapFirstName();
        Person expected = new Person(3L,"Durand", "Noelle", "395-6161", "1960-07-06", 1L);
        for(Person p : actual.get("Noelle")){
            assertEquals(expected.getBirthday(), p.getBirthday());
            assertEquals(expected.getLastName(), p.getLastName());
            assertEquals(expected.getFirstName(), p.getFirstName());
            assertEquals(expected.getHomeId(), p.getHomeId());
            assertEquals(expected.getMobile(), p.getMobile());
        }

    }

    @Test
    public void getSurNameReportTest(){
        Map<String, Integer> actual = service.getSurNameReport();
        assertEquals(2, actual.get("Smith"));
    }

    @Test
    public void getFirstNameReportTest(){
        Map<String, Integer> actual = service.getFirstNameReport();
        assertEquals(1, actual.get("Jane"));
    }
}