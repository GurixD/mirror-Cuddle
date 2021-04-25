package ch.hearc.cuddle.controllers;

import ch.hearc.cuddle.auth.repository.UserRepository;
import ch.hearc.cuddle.models.Animal;
import ch.hearc.cuddle.models.Breed;
import ch.hearc.cuddle.models.Species;
import ch.hearc.cuddle.service.AnimalService;
import ch.hearc.cuddle.service.BreedService;
import ch.hearc.cuddle.service.SpeciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AdoptController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AnimalService animalService;
    @Autowired
    BreedService breedService;
    @Autowired
    SpeciesService speciesService;

    @GetMapping({"/adopt"})
    public String adopt(Model model, @RequestParam(required = false) Integer speciesID, @RequestParam(required = false) Integer breedID) {
        System.out.println(speciesID);
        System.out.println(breedID);
        List<Animal> listAnimals = animalService.findAll();
        List<Breed> listBreed = breedService.findAll();
        List<Species> listSpecies = speciesService.findAll();

        if (speciesID != null)
            listAnimals = listAnimals.stream().filter(animal -> animal.getSpecies().getId() == speciesID.intValue())
                    .collect(Collectors.toList());

        if (breedID != null)
            listAnimals = listAnimals.stream().filter(animal -> animal.getBreed().getId() == breedID.intValue())
                    .collect(Collectors.toList());

        model.addAttribute("listAnimals", listAnimals);
        model.addAttribute("listBreed", listBreed);
        model.addAttribute("listSpecies", listSpecies);
        model.addAttribute("speciesID", speciesID);
        model.addAttribute("breedID", breedID);
        return "adopt";
    }


}