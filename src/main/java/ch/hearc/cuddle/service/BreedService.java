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

    public List<Breed> findByName(String name) {
        return breedRepo.findByName(name);
    }

    public Breed save(Breed breed) {
        if (StringUtils.hasText(breed.getName())) {

            if (breed.getId() != null && existsById(breed.getId())) {
                System.out.println("Breed with id already exists");
            } else
                return breedRepo.save(breed);
        }

        return null;
    }

    public boolean update(Breed breed) {
        if (StringUtils.hasText(breed.getName())) {
            if (!existsById(breed.getId())) {
                System.out.println("Cannot find Breed with id");
                return false;
            } else {
                breedRepo.save(breed);
                return true;
            }
        }

        return false;
    }

    public boolean deleteById(Long id) {
        try {
            if (existsById(id)) {
                breedRepo.deleteById(id);
                return true;
            }
        } catch (Exception e) {
            return false;
        }

        return false;
    }

    public Long count() {
        return breedRepo.count();
    }

    public Breed toBreed(DatabaseEnum dbEnum) {
        Breed b = new Breed();
        b.setId(dbEnum.getId());
        b.setName(dbEnum.getName());

        return b;
    }
}
