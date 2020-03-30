package io.zipcoder.persistenceapp.jdbc;

import io.zipcoder.persistenceapp.models.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonRowMapper implements RowMapper<Person> {

    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {

        Person person = new Person();
        person.setId(rs.getLong("ID"));
        person.setFirstName(rs.getString("FIRST_NAME"));
        person.setLastName(rs.getString("LAST_NAME"));
        person.setMobile(rs.getString("MOBILE"));
        person.setBirthday(rs.getString("BIRTHDAY"));
        person.setHomeId(rs.getLong("HOME_ID"));

        return person;

    }
}

