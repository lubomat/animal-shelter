package pl.animalshelter.animalshelter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/animals")
public class AnimalController {

    @Autowired
    AnimalRepository animalRepository;


    @GetMapping("/test")
    public int test() {
        return 1;
    }



    @GetMapping("")
    public List<Animal> getAll() {
        return animalRepository.getAll();
    }

    @GetMapping("{id}")
    public Animal getById(@PathVariable("id") int id) {
        return animalRepository.getById(id);
    }

    @PostMapping("")
    public int add(@RequestBody List<Animal> animals) {
        return animalRepository.save(animals);
    }

    @PutMapping("/{id}")
    public int update(@PathVariable("id") int id, @RequestBody Animal updatedAnimal) {
        Animal animal = animalRepository.getById(id);

        if (animal != null) {
            animal.setName(updatedAnimal.getName());
            animal.setRace(updatedAnimal.getRace());
            animal.setAge(updatedAnimal.getAge());

            animalRepository.update(animal);

            return 1;
        } else {
            return -1;
        }
    }
}
