package ch.hearc.cuddle.controllers;

import ch.hearc.cuddle.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DetailsController {

    @Autowired
    UserRepository userRepository;


    @GetMapping({"/details/{id}"})
    public String home(@PathVariable("id")String id) {
        return "details";
    }


}
