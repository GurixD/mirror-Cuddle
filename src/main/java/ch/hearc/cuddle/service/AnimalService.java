package ch.hearc.cuddle.service;

import ch.hearc.cuddle.models.Animal;
import ch.hearc.cuddle.models.Breed;
import ch.hearc.cuddle.models.DatabaseEnum;
import ch.hearc.cuddle.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class AnimalService {
    @Autowired
    private AnimalRepository animalRepo;

    public List<Animal> findAll() {
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

    public List<Animal> findByName(String name) {
        return animalRepo.findByName(name);
    }

    public List<Animal> getRandom(int number) {
        List<Animal> animals = animalRepo.findAll();
        Collections.shuffle(animals);
        return animals.subList(0, number - 1);
    }

    public List<Animal> findAll(int pageNumber, int rowPerPage) {
        List<Animal> animals = new ArrayList<>();
        Pageable sortedByIdAsc = PageRequest.of(pageNumber - 1, rowPerPage,
                Sort.by("id").ascending());
        animalRepo.findAll(sortedByIdAsc).forEach(animals::add);
        return animals;
    }

    public long count() {
        return animalRepo.count();
    }

    public Animal findById(long id) {
        return animalRepo.findById(id).orElse(null);
    }

    private boolean existsById(Long id) {
        return animalRepo.existsById(id);
    }

    public boolean update(Animal animal) {
        if (StringUtils.hasText(animal.getName())) {
            if (!existsById(animal.getId())) {
                System.out.println("Cannot find animal with id");
                return false;
            } else {
                animalRepo.save(animal);
                return true;
            }
        }

        return false;
    }

    public boolean deleteById(Long id) {
        try {
            if (existsById(id)) {
                animalRepo.deleteById(id);
                return true;
            }
        } catch (Exception e) {
            return false;
        }

        return false;
    }
}
