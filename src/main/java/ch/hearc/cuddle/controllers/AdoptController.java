package ch.hearc.cuddle.controllers;

import ch.hearc.cuddle.auth.repository.UserRepository;
import ch.hearc.cuddle.models.Animal;
import ch.hearc.cuddle.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class AdoptController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AnimalService animalService;

    @GetMapping({"/adopt"})
    public String adopt(Model model) {
        List<Animal> listAnimals = animalService.listAll();
        model.addAttribute("listAnimals", listAnimals);
        return "adopt";
    }


}