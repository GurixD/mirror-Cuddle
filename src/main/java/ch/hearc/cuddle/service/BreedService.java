package ch.hearc.cuddle.service;

import ch.hearc.cuddle.models.Breed;
import ch.hearc.cuddle.models.DatabaseEnum;
import ch.hearc.cuddle.repository.BreedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class BreedService {
    @Autowired
    private BreedRepository breedRepo;

    private boolean existsById(Long id) {
        return breedRepo.existsById(id);
    }

    public Breed findById(Long id) {
        return breedRepo.findById(id).orElse(null);
    }

    public List<Breed> findAll() {
        return breedRepo.findAll();
    }

    public List<Breed> findAll(int pageNumber, int rowPerPage) {
        List<Breed> breeds = new ArrayList<>();
        Pageable sortedByIdAsc = PageRequest.of(pageNumber - 1, rowPerPage,
                Sort.by("id").ascending());
        breedRepo.findAll(sortedByIdAsc).forEach(breeds::add);
        return breeds;
    }

    public Breed save(DatabaseEnum breed) {
        if (!StringUtils.hasText(breed.getName())) {

            if (breed.getId() != null && existsById(breed.getId())) {
                System.out.println("Breed with id already exists");
            } else
                return breedRepo.save((Breed) breed);
        }

        return null;
    }

    public void update(DatabaseEnum breed) {
        if (StringUtils.hasText(breed.getName())) {
            if (!existsById(breed.getId())) {
                System.out.println("Cannot find Breed with id");
            } else
                breedRepo.save((Breed)breed);
        }
    }

    public void deleteById(Long id) {
        if (existsById(id)) {
            breedRepo.deleteById(id);
        }
    }

    public Long count() {
        return breedRepo.count();
    }
}
