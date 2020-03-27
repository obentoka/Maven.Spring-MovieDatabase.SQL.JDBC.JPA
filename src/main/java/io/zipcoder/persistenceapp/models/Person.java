package io.zipcoder.persistenceapp.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Person {
    @Id
    Long id;
}
