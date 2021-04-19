package ch.hearc.cuddle.service;

import ch.hearc.cuddle.models.Breed;
import ch.hearc.cuddle.repository.BreedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BreedService {
    @Autowired
    private BreedRepository breedRepo;

    public List<Breed> findAll() {
        return breedRepo.findAll();
    }


}
