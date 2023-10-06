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
        return jdbcTemplate.query("SELECT id, name, race, age FROM animal",
                BeanPropertyRowMapper.newInstance(Animal.class));
    }

    public Animal getById(int id) {
        return jdbcTemplate.queryForObject("SELECT id, name, race, age FROM animal WHERE " +
                "id = ?", BeanPropertyRowMapper.newInstance(Animal.class), id);

    }

    public int save(List<Animal> animals) {
        animals.forEach(animal -> jdbcTemplate
                .update("INSERT INTO animal(name, race, age) VALUES (?, ?, ?)",
                        animal.getName(),animal.getRace(),animal.getAge()
                ));
        return 1;
    }

    public int update(Animal animal) {
        return jdbcTemplate.update("UPDATE animal SET name=?, race=?, age=? WHERE id=?",
                animal.getName(), animal.getRace(), animal.getAge(), animal.getId());
    }

    public int delete(int id) {
        return jdbcTemplate.update("DELETE FROM animal WHERE id=?", id);
    }
}
