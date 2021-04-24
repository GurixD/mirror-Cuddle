package ch.hearc.cuddle.controllers;

import ch.hearc.cuddle.models.Animal;
import ch.hearc.cuddle.models.Animal;
import ch.hearc.cuddle.models.Breed;
import ch.hearc.cuddle.models.Species;
import ch.hearc.cuddle.service.AnimalService;
import ch.hearc.cuddle.service.BreedService;
import ch.hearc.cuddle.service.SpeciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

        System.out.println(animal.getImage());
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
    public String add(Model model, @ModelAttribute("animal") Animal newAnimal) {
        Animal animal = animalService.save(newAnimal);

        if (animal != null)
            return "redirect:/dashboard/animals/" + animal.getId();

        model.addAttribute("animal", newAnimal);
        model.addAttribute("errorMessage", "Failed to add");
        model.addAttribute("add", true);

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
    public String update(Model model, @PathVariable long id, @ModelAttribute("animal") Animal animal) {
        animal.setId(id);

        boolean ok = animalService.update(animal);
        if (ok) {
            return "redirect:/dashboard/animals/" + animal.getId();
        }

        model.addAttribute("errorMessage", "Animal not found");
        model.addAttribute("add", false);

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

}
