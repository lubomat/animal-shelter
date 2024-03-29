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

    @GetMapping("/{id}")
    public Animal getById(@PathVariable("id") int id) {
        return animalRepository.getById(id);
    }

    @PostMapping("")
    public int add(@RequestBody List<Animal> animals) {
        return animalRepository.save(animals);
    }

    @PutMapping("/{id}")          // przy tej metodzie trzeba podawac wszystkie parametry
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
    @PatchMapping("/{id}")
    public int partiallyUpdate(@PathVariable("id") int id, @RequestBody Animal updatedAnimal) {
        Animal animal = animalRepository.getById(id);

        if (animal != null) {
            if (updatedAnimal.getName() != null) animal.setName(updatedAnimal.getName());
            if (updatedAnimal.getRace() != null) animal.setName(updatedAnimal.getName());
            if (updatedAnimal.getAge() > 0) animal.setAge(updatedAnimal.getAge());

            animalRepository.update(animal);

            return 1;
        }else {
            return -1;
        }
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable("id") int id) {
        return animalRepository.delete(id);
    }
}
