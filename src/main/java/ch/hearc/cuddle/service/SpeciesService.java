package ch.hearc.cuddle.service;

import ch.hearc.cuddle.models.Species;
import ch.hearc.cuddle.repository.SpeciesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpeciesService {
    @Autowired
    private SpeciesRepository speciesRepo;

    public List<Species> findAll() {
        return speciesRepo.findAll();
    }
}
