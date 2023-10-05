package pl.animalshelter.animalshelter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AnimalRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Animal> getAll() {
        return jdbcTemplate.query("SELECT id, name, race FROM animal",
                BeanPropertyRowMapper.newInstance(Animal.class));
    }

    public Animal getById(int id) {
        return jdbcTemplate.queryForObject("SELECT id, name, race FROM animal WHERE " +
                "id = ?", BeanPropertyRowMapper.newInstance(Animal.class), id);

    }
}
