package io.zipcoder.persistenceapp.repositories;

import io.zipcoder.persistenceapp.models.Person;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

    Iterable<Person> findAllByFirstName(String firstName);

    Iterable<Person> findAllByLastName(String lastName);

    Iterable<Person> findAllByBirthday(String DOB);

    Iterable<Person> findAllByMobile(String mobile);

    Iterable<Person> findAllByHomeId(Long homeId);

    @Query("SELECT DISTINCT p.firstName FROM Person p")
    List<String> findDistinctFirstName();

    @Query("SELECT DISTINCT p.lastName FROM Person p")
    List<String> findDistinctLastName();

    Integer countPeopleByLastName(String lastName);

    Integer countPeopleByFirstName(String firstName);


}
