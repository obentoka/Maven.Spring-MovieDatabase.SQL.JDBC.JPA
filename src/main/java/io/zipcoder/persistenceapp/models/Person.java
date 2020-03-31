package io.zipcoder.persistenceapp.models;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Person {

    @Id
    private Long id;
    private String lastName;
    private String firstName;
    private String mobile;
    private String birthday;
    private Long homeId;

    public Person(Long id, String lastName, String firstName,
                  String mobile, String birthday, Long homeId) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.mobile = mobile;
        this.birthday = birthday;
        this.homeId = homeId;
    }

    public Person(String lastName, String firstName, String mobile,
                  String birthday, Long homeId) {
        this.id = null;
        this.lastName = lastName;
        this.firstName = firstName;
        this.mobile = mobile;
        this.birthday = birthday;
        this.homeId = homeId;
    }

    public Person(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Long getHomeId() {
        return homeId;
    }

    public void setHomeId(Long homeId) {
        this.homeId = homeId;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) &&
                Objects.equals(firstName, person.firstName) &&
                Objects.equals(lastName, person.lastName) &&
                Objects.equals(mobile, person.mobile) &&
                Objects.equals(birthday, person.birthday) &&
                Objects.equals(homeId, person.homeId);
    }
}
