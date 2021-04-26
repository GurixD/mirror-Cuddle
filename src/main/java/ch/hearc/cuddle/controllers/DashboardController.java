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
//        if (speciesID != null)
//            listAnimals = listAnimals.stream().filter(animal -> animal.getSpecies().getId() == speciesID.intValue())
//                    .collect(Collectors.toList());
//
//        if (breedID != null)
//            listAnimals = listAnimals.stream().filter(animal -> animal.getBreed().getId() == breedID.intValue())
//                    .collect(Collectors.toList());

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

    @GetMapping("/addAnimal")
    public String addAnimal(Model model) {

        addAnimalModel(model, new Animal());

        return "addAnimal";
    }

    private void addAnimalModel(Model model, Animal animal) {
        List<Species> species = speciesService.findAll();
        List<Breed> breeds = breedService.findAll();

        model.addAttribute("species", species);
        model.addAttribute("breeds", breeds);
        model.addAttribute("newAnimal", animal);
    }

    @PostMapping("/addAnimal")
    public String addAnimal(@ModelAttribute("newAnimal") Animal newAnimal, @RequestParam("formImage") MultipartFile multipartFile, BindingResult bindingResult, Model model
                            ) throws IOException {

        String fileExt = FilenameUtils.getExtension(multipartFile.getOriginalFilename()).toLowerCase();
        String fileName = UUID.randomUUID() + "." + fileExt;
        String[] mimeTypes = {"image/png", "image/jpeg", "image/jpg", "image/gif"};

        if (!multipartFile.isEmpty() && Arrays.asList(mimeTypes).contains(multipartFile.getContentType()))
        {
            newAnimal.setImage(fileName);
        }

        animalValidator.validate(newAnimal, bindingResult);

        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            for (ObjectError error : errors) {
                System.out.println(error);
            }

            addAnimalModel(model, newAnimal);
            return "addAnimal";
        }

        Animal savedAnimal = animalService.save(newAnimal);
        String uploadDir = "media/img/animal/" + savedAnimal.getId();
        FileHelper.saveFile(uploadDir, fileName, multipartFile);
        return "redirect:/home";
    }
}
