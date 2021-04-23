package ch.hearc.cuddle.controllers;

import ch.hearc.cuddle.models.Animal;
import ch.hearc.cuddle.models.Breed;
import ch.hearc.cuddle.models.Species;
import ch.hearc.cuddle.service.BreedService;
import ch.hearc.cuddle.service.SpeciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

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
    public String addAnimal(@ModelAttribute("newAnimal") Animal newAnimal) {

        System.out.println(newAnimal.getName());
        System.out.println(newAnimal.getSex());
        System.out.println(newAnimal.getDescription());
        System.out.println(newAnimal.getAge());

        return "redirect:/home";
    }
}
