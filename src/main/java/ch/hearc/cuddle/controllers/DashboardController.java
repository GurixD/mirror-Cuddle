package ch.hearc.cuddle.controllers;

import ch.hearc.cuddle.auth.service.UserService;
import ch.hearc.cuddle.models.Animal;
import ch.hearc.cuddle.models.Breed;
import ch.hearc.cuddle.models.DatabaseEnum;
import ch.hearc.cuddle.models.Species;
import ch.hearc.cuddle.repository.AnimalRepository;
import ch.hearc.cuddle.service.AnimalService;
import ch.hearc.cuddle.service.BreedService;
import ch.hearc.cuddle.service.SpeciesService;
import ch.hearc.cuddle.validator.AnimalValidator;
import org.apache.commons.io.FilenameUtils;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.List;
import java.util.UUID;
import java.util.Map;
import java.util.stream.Collector;
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
    AnimalService animalService;

    @Autowired
    private AnimalValidator animalValidator;

    @GetMapping("")
    public String dashboard(Model model) {
        List<Species> species = speciesService.findAll();
        List<Breed> breeds = breedService.findAll();
        List<Animal> animals = animalService.listAll();
        Map<String, Animal[]> animalDict = species.stream() //
                .parallel() //
                .collect(Collectors //
                        .toMap(DatabaseEnum::getName, //
                                l -> animals //
                                        .stream() //
                                        .parallel() //
                                        .filter(a -> a //
                                                .getSpecies() //
                                                .getId() //
                                                .equals(l.getId())) //
                                        .toArray(Animal[]::new))); //

        model.addAttribute("animalDict", animalDict);
        model.addAttribute("species", species);
        model.addAttribute("breeds", breeds);


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
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        return "redirect:/home";
    }
}

class FileUploadUtil {

    public static void saveFile(String uploadDir, String fileName,
                                MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save image file: " + fileName, ioe);
        }
    }
}
