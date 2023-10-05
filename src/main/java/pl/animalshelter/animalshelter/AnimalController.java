package pl.animalshelter.animalshelter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AnimalController {

    @Autowired
    AnimalRepository animalRepository;


    @GetMapping("/test")
    public int test() {
        return 1;
    }



    @GetMapping("/animals")
    public List<Animal> getAll() {
        return animalRepository.getAll();
    }

    @GetMapping("/animals/{id}")
    public Animal getById(@PathVariable("id") int id) {
        return animalRepository.getById(id);
    }

}
