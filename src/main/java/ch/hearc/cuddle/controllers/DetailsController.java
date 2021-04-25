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
public class DetailsController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AnimalService animalService;

    @GetMapping({"/details/{id}"})
    public String details(@PathVariable("id")String id, Model model) {
        Animal animal = animalService.get(Long.parseLong(id));
        model.addAttribute("animal", animal);
        return "details";
    }


}
