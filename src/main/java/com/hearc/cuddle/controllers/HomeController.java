package com.hearc.cuddle.controllers;

import com.hearc.cuddle.auth.repository.UserRepository;
import com.hearc.cuddle.models.Animal;
import com.hearc.cuddle.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AnimalService animalService;

    @GetMapping({"/", "/home"})
    public String home(Model model) {
        return "home";
    }

    @GetMapping({"/hello"})
    public String hello(Model model) {
        return "hello";
    }

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        List<Animal> animalsList = animalService.findByName("Billy");
        System.out.println(animalsList);
        model.addAttribute("name", name);
        model.addAttribute("listAnimals", animalsList);
        return "greeting";
    }

//    @GetMapping("/hello")
//    public String hello() {
//        return "hello";
//    }
//
//    // Login form
//    @RequestMapping("/login")
//    public String login() {
//        return "login";
//    }
//
//    // Login form with error
//    @RequestMapping("/login-error")
//    public String loginError(Model model) {
//        model.addAttribute("loginError", true);
//        return "login";
//    }

}
