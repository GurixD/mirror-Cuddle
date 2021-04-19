package com.hearc.cuddle.service;

import com.hearc.cuddle.models.Animal;
import com.hearc.cuddle.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
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

    public void save(Animal product) {
        animalRepo.save(product);
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
