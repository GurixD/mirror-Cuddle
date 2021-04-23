package ch.hearc.cuddle.controllers;

import ch.hearc.cuddle.models.Animal;
import ch.hearc.cuddle.models.Breed;
import ch.hearc.cuddle.models.Species;
import ch.hearc.cuddle.repository.AnimalRepository;
import ch.hearc.cuddle.service.AnimalService;
import ch.hearc.cuddle.service.BreedService;
import ch.hearc.cuddle.service.SpeciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    AnimalService animalService;

    @Autowired
    SpeciesService speciesService;

    @Autowired
    BreedService breedService;


    @GetMapping("/addAnimal")
    public String addAnimal(Model model) {
        List<Species> species = speciesService.findAll();
        List<Breed> breeds = breedService.findAll();
        Animal newAnimal = new Animal();

        model.addAttribute("species", species);
        model.addAttribute("breeds", breeds);
        model.addAttribute("newAnimal", newAnimal);

        return "addAnimal";
    }

    @PostMapping("/addAnimal")
    public String addAnimal(@ModelAttribute("newAnimal") Animal newAnimal,
                            @RequestParam("formImage") MultipartFile multipartFile) throws IOException {

//        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        String fileName = UUID.randomUUID().toString();
        newAnimal.setImage(fileName);

        System.out.println(fileName);
        System.out.println(newAnimal.getId());
        System.out.println(newAnimal.getImage());
        System.out.println(newAnimal.getName());
        System.out.println(newAnimal.getSex());
        System.out.println(newAnimal.getDescription());
        System.out.println(newAnimal.getAge());

        Animal savedAnimal = animalService.save(newAnimal);

        String uploadDir = "media/img/animal/" + savedAnimal.getId();

        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
//        return "redirect:/home";
        return "addAnimal";
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
