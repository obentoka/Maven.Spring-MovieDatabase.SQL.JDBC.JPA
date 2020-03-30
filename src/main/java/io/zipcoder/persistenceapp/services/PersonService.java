package io.zipcoder.persistenceapp.services;

import io.zipcoder.persistenceapp.jdbc.PersonRowMapper;
import io.zipcoder.persistenceapp.models.Person;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Service
public class PersonService {

    private DriverManagerDataSource dataSource = new DriverManagerDataSource("jdbc:h2:mem:testdb", "sa","");
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

    public Person findPersonById(Long id){
        try {
            String sql = "SELECT * FROM PERSON WHERE ID = ?;";
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, new PersonRowMapper());
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public Iterable<Person> findAll(){
        String sql = "SELECT * FROM PERSON;";
        return jdbcTemplate.query(sql, new PersonRowMapper());
    }

    public Person createPerson(Person person) {
        String sql = "INSERT INTO PERSON (LAST_NAME, FIRST_NAME, MOBILE, BIRTHDAY, HOME_ID)" +
                "VALUES(?,?,?,?,?);";
        KeyHolder holder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, person.getLastName());
            ps.setString(2, person.getFirstName());
            ps.setString(3, person.getMobile());
            ps.setString(4, person.getBirthday());
            ps.setLong(5, person.getHomeId());
            return ps;
        }, holder);

        Long newPersonId = holder.getKey().longValue();
        return findPersonById(newPersonId);
    }

    public Person updatePerson(Long id, Person person){
        if(findPersonById(id) != null) {
            String sql = "UPDATE PERSON SET LAST_NAME = ?, FIRST_NAME = ?, " +
                    "MOBILE = ?, BIRTHDAY = ?, HOME_ID = ? WHERE ID = ?;";
            jdbcTemplate.update(sql, person.getLastName(), person.getFirstName(), person.getMobile(),
                    person.getBirthday(), person.getHomeId(), id);
            return findPersonById(id);
        }
        else
            return createPerson(person);
    }

    public Boolean deletePerson(Long id){
        if(findPersonById(id) != null) {
            String sql = "DELETE FROM PERSON WHERE ID = ?;";
            jdbcTemplate.update(sql, id);
            return true;
        }
        else
            return false;
    }

    public Iterable<Person> findAllByFirstName(String firstName){
        String sql = "SELECT * FROM PERSON WHERE FIRST_NAME = ?;";
        return jdbcTemplate.query(sql, new Object[]{firstName}, new PersonRowMapper());
    }

    public Iterable<Person> findAllByLastName(String lastName){
        String sql = "SELECT * FROM PERSON WHERE LAST_NAME = ?;";
        return jdbcTemplate.query(sql, new Object[]{lastName}, new PersonRowMapper());
    }

    public Iterable<Person> findAllByDOB(String dob){
        String sql = "SELECT * FROM PERSON WHERE BIRTHDAY = ?;";
        return jdbcTemplate.query(sql, new Object[]{dob}, new PersonRowMapper());
    }

    public Iterable<Person> findAllByMobile(String moible){
        String sql = "SELECT * FROM PERSON WHERE MOBILE = ?;";
        return jdbcTemplate.query(sql, new Object[]{moible}, new PersonRowMapper());
    }

    public Iterable<Person> findAllByHomeId(Long homeid){
        String sql = "SELECT * FROM PERSON WHERE HOME_ID = ?;";
        return jdbcTemplate.query(sql, new Object[]{homeid}, new PersonRowMapper());
    }
}
