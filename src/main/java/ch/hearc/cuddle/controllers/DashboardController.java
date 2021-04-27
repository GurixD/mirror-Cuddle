package ch.hearc.cuddle.controllers;

import ch.hearc.cuddle.helpers.FileHelper;
import ch.hearc.cuddle.models.Animal;
import ch.hearc.cuddle.models.Breed;
import ch.hearc.cuddle.models.DatabaseEnum;
import ch.hearc.cuddle.models.Species;
import ch.hearc.cuddle.service.AnimalService;
import ch.hearc.cuddle.service.BreedService;
import ch.hearc.cuddle.service.SpeciesService;
import ch.hearc.cuddle.validator.AnimalValidator;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    AnimalService animalService;

    @Autowired
    SpeciesService speciesService;

    @Autowired
    BreedService breedService;

    @Autowired
    private AnimalValidator animalValidator;

    @GetMapping("")
    public String dashboard(Model model, @RequestParam(required = false) Integer speciesID, @RequestParam(required = false) Integer breedID, @RequestParam(required = false) String hasTreatment) {
        List<Species> species = speciesService.findAll();
        List<Breed> breeds = breedService.findAll();
        List<Animal> animals = animalService.findAll();

        Map<String, Animal[]> animalDict = species.stream() //
                .parallel()//
                .filter(s -> speciesID == null || s.getId() == speciesID.intValue())
                .collect(Collectors //
                        .toMap(Species::getName, //
                                l -> animals //
                                        .stream() //
                                        .parallel() //
                                        .filter(a -> a //
                                                .getSpecies() //
                                                .getId() //
                                                .equals(l.getId()))
                                        .filter(a -> breedID == null || a.getBreed().getId() == breedID.intValue())//
                                        .filter(a -> hasTreatment == null || !a.getTreatment().isEmpty())//
                                        .toArray(Animal[]::new))); //

        animalDict = animalDict.entrySet().stream().parallel().filter(entry -> entry.getValue().length > 0).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        model.addAttribute("listBreed", breeds);
        model.addAttribute("listSpecies", species);
        model.addAttribute("animalDict", animalDict);
        model.addAttribute("species", species);
        model.addAttribute("breeds", breeds);
        model.addAttribute("speciesID", speciesID);
        model.addAttribute("breedID", breedID);
        model.addAttribute("hasTreatment", hasTreatment);


        return "dashboard";
    }
}
