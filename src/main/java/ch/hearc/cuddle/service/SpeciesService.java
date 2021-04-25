package ch.hearc.cuddle.service;

import ch.hearc.cuddle.models.Breed;
import ch.hearc.cuddle.models.DatabaseEnum;
import ch.hearc.cuddle.models.Species;
import ch.hearc.cuddle.repository.SpeciesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpeciesService {
    @Autowired
    private SpeciesRepository speciesRepo;

    private boolean existsById(Long id) {
        return speciesRepo.existsById(id);
    }

    public Species findById(Long id) {
        return speciesRepo.findById(id).orElse(null);
    }

    public List<Species> findAll() {
        return speciesRepo.findAll();
    }

    public List<Species> findAll(int pageNumber, int rowPerPage) {
        List<Species> species = new ArrayList<>();
        Pageable sortedByIdAsc = PageRequest.of(pageNumber - 1, rowPerPage,
                Sort.by("id").ascending());
        speciesRepo.findAll(sortedByIdAsc).forEach(species::add);
        return species;
    }

    public List<Species> findByName(String name) {
        return speciesRepo.findByName(name);
    }

    public Species save(Species species) {
        if (StringUtils.hasText(species.getName())) {

            if (species.getId() != null && existsById(species.getId())) {
                System.out.println("Species with id already exists");
            } else
                return speciesRepo.save(species);
        }

        return null;
    }

    public boolean update(Species species) {
        if (StringUtils.hasText(species.getName())) {
            if (!existsById(species.getId())) {
                System.out.println("Cannot find Species with id");
                return false;
            } else {
                speciesRepo.save(species);
                return true;
            }
        }

        return false;
    }

    public boolean deleteById(Long id) {
        try {
            if (existsById(id)) {
                speciesRepo.deleteById(id);
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public Long count() {
        return speciesRepo.count();
    }

    public Species toSpecies(DatabaseEnum dbEnum) {
        Species species = new Species();
        species.setId(dbEnum.getId());
        species.setName(dbEnum.getName());

        return species;
    }
}
