package ch.hearc.cuddle.controllers;

import ch.hearc.cuddle.helpers.FileHelper;
import ch.hearc.cuddle.models.Animal;
import ch.hearc.cuddle.models.Breed;
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
import java.util.UUID;

@Controller
@RequestMapping("/dashboard/animals")
public class AnimalController {

    private final int ROW_PER_PAGE = 10;

    @Autowired
    private AnimalService animalService;

    @Autowired
    private SpeciesService speciesService;

    @Autowired
    private BreedService breedService;

    @Autowired
    private AnimalValidator animalValidator;

    @GetMapping(value = "")
    public String get(Model model, @RequestParam(value = "page", defaultValue = "1") int pageNumber) {
        List<Animal> animals = animalService.findAll(pageNumber, ROW_PER_PAGE);
        long count = animalService.count();

        boolean hasPrev = pageNumber > 1;
        boolean hasNext = ((long) pageNumber * ROW_PER_PAGE) < count;
        model.addAttribute("animals", animals);
        model.addAttribute("hasPrev", hasPrev);
        model.addAttribute("prev", pageNumber - 1);
        model.addAttribute("hasNext", hasNext);
        model.addAttribute("next", pageNumber + 1);

        return "animals/get";
    }


    @GetMapping(value = "/{id}")
    public String getById(Model model, @PathVariable long id) {
        Animal animal = animalService.findById(id);

        if (animal == null) {
            model.addAttribute("errorMessage", "Animal not found");
        }
        model.addAttribute("animal", animal);

        return "animals/getById";
    }


    @GetMapping(value = {"/add"})
    public String showAdd(Model model) {
        Animal animal = new Animal();
        List<Species> species = speciesService.findAll();
        List<Breed> breeds = breedService.findAll();

        model.addAttribute("add", true);
        model.addAttribute("animal", animal);
        model.addAttribute("species", species);
        model.addAttribute("breeds", breeds);

        return "animals/edit";
    }


    @PostMapping(value = "/add")
    public String add(Model model, @ModelAttribute("animal") Animal newAnimal, @RequestParam("formImage") MultipartFile multipartFile, BindingResult errors) throws IOException {
        String fileExt = FilenameUtils.getExtension(multipartFile.getOriginalFilename()).toLowerCase();
        String fileName = UUID.randomUUID() + "." + fileExt;

        List<Species> species = speciesService.findAll();
        List<Breed> breeds = breedService.findAll();

        model.addAttribute("animal", newAnimal);
        model.addAttribute("add", true);
        model.addAttribute("species", species);
        model.addAttribute("breeds", breeds);

        animalValidator.validate(animalValidator, errors);

        if (multipartFile.isEmpty() || !Arrays.asList(FileHelper.ALLOWED_FILES).contains(multipartFile.getContentType())) {
            errors.addError(new ObjectError("formImage", "Image is empty"));

        }

        if (!errors.hasErrors()) {
            newAnimal.setImage(fileName);
            newAnimal.setTreatment("");
            Animal animal = animalService.save(newAnimal);

            if (animal != null) {
                String uploadDir = "/app/static/media/img/animal/" + animal.getId();
                FileHelper.saveFile(uploadDir, fileName, multipartFile);
                return "redirect:/dashboard/animals/" + animal.getId();
            } else
                errors.addError(new ObjectError("animal", "Failed to save animal"));
        }

        StringBuilder errorsString = new StringBuilder();
        for (ObjectError er : errors.getAllErrors()) {
            errorsString.append(er.getDefaultMessage()).append("<br>");
        }

        model.addAttribute("errorMessage", errorsString.toString());

        return "animals/edit";
    }


    @GetMapping(value = {"/{id}/edit"})
    public String showEdit(Model model, @PathVariable long id) {
        Animal animal = animalService.findById(id);
        List<Species> species = speciesService.findAll();
        List<Breed> breeds = breedService.findAll();

        if (animal == null)
            model.addAttribute("errorMessage", "Animal not found");

        model.addAttribute("add", false);
        model.addAttribute("animal", animal);
        model.addAttribute("species", species);
        model.addAttribute("breeds", breeds);

        return "animals/edit";
    }


    @PostMapping(value = {"/{id}/edit"})
    public String update(Model model, @PathVariable long id, @ModelAttribute("animal") Animal newAnimal, @RequestParam("formImage") MultipartFile multipartFile, BindingResult errors) throws IOException {
        Animal oldAnimal = animalService.findById(id);
        newAnimal.setId(id);
        newAnimal.setTreatment(oldAnimal.getTreatment());
        String fileExt = FilenameUtils.getExtension(multipartFile.getOriginalFilename()).toLowerCase();
        String fileName = UUID.randomUUID() + "." + fileExt;

        List<Species> species = speciesService.findAll();
        List<Breed> breeds = breedService.findAll();

        model.addAttribute("animal", newAnimal);
        model.addAttribute("add", false);
        model.addAttribute("species", species);
        model.addAttribute("breeds", breeds);

        animalValidator.validate(animalValidator, errors);

        if (!errors.hasErrors()) {

            boolean newImage = false;
            String oldImage = oldAnimal.getImage();

            if (multipartFile.isEmpty() || !Arrays.asList(FileHelper.ALLOWED_FILES).contains(multipartFile.getContentType())) {
                newAnimal.setImage(oldAnimal.getImage());
            } else {
                newAnimal.setImage(fileName);
                newImage = true;
            }

            boolean updated = animalService.update(newAnimal);

            if (updated) {
                if (newImage) {
                    String uploadDir = "/app/static/media/img/animal/" + newAnimal.getId();
                    FileHelper.saveFile(uploadDir, fileName, multipartFile);
                    FileHelper.deleteFile(uploadDir, oldImage);
                }
                return "redirect:/dashboard/animals/" + newAnimal.getId();
            } else
                errors.addError(new ObjectError("animal", "Failed to save animal"));
        }

        StringBuilder errorsString = new StringBuilder();
        for (ObjectError er : errors.getAllErrors()) {
            errorsString.append(er.getDefaultMessage()).append("<br>");
        }

        model.addAttribute("errorMessage", errorsString.toString());

        return "animals/edit";
    }


    @GetMapping(value = {"/{id}/delete"})
    public String showDeleteById(Model model, @PathVariable long id) {
        Animal animal = animalService.findById(id);

        if (animal == null) {
            model.addAttribute("errorMessage", "Animal not found");
        } else {
            model.addAttribute("animal", animal);
        }

        model.addAttribute("allowDelete", true);

        return "animals/getById";
    }


    @PostMapping(value = {"/{id}/delete"})
    public String deleteById(Model model, @PathVariable long id) {
        Animal animal = animalService.findById(id);
        boolean ok = animalService.deleteById(id);

        if (ok)
            return "redirect:/dashboard/animals";

        model.addAttribute("animal", animal);
        model.addAttribute("errorMessage", "Could not delete");

        return "animals/getById";
    }

    @GetMapping(value = {"/{id}/editTreatment"})
    public String showEditTreatment(Model model, @PathVariable long id) {
        Animal animal = animalService.findById(id);

        if (animal == null)
            model.addAttribute("errorMessage", "Animal not found");

        model.addAttribute("animal", animal);
        return "animals/editTreatment";
    }

    @PostMapping(value = {"/{id}/editTreatment"})
    public String updateTreatment(Model model, @PathVariable long id, @ModelAttribute("animal") Animal newAnimal, BindingResult errors) throws IOException {
        Animal oldAnimal = animalService.findById(id);
        oldAnimal.setTreatment(newAnimal.getTreatment());
        model.addAttribute("animal", oldAnimal);

        boolean updated = animalService.update(oldAnimal);

        if (updated) {
            return "redirect:/dashboard/animals/" + newAnimal.getId();
        } else
            errors.addError(new ObjectError("animal", "Failed to update treatment"));

        StringBuilder errorsString = new StringBuilder();
        for (ObjectError er : errors.getAllErrors()) {
            errorsString.append(er.getDefaultMessage()).append("<br>");
        }

        model.addAttribute("errorMessage", errorsString.toString());

        return "animals/editTreatment";
    }
}
