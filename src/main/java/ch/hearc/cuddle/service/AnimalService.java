package ch.hearc.cuddle.service;

import ch.hearc.cuddle.models.Animal;
import ch.hearc.cuddle.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AnimalService {
    @Autowired
    private AnimalRepository animalRepo;

    public List<Animal> listAll() {
        return animalRepo.findAll();
    }

    public Animal save(Animal product) {
        return animalRepo.save(product);
    }

    public Animal get(long id) {
        return animalRepo.findById(id).get();
    }

    public void delete(long id) {
        animalRepo.deleteById(id);
    }

    public List<Animal> findByName(String name){return animalRepo.findByName(name);}

    public List<Animal> getRandom(int number)
    {
        List<Animal> animals = animalRepo.findAll();
        Collections.shuffle(animals);
        return animals.subList(0, number-1);
    }

}
