package ch.hearc.cuddle.controllers;

import ch.hearc.cuddle.auth.repository.UserRepository;
import ch.hearc.cuddle.models.Animal;
import ch.hearc.cuddle.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AnimalService animalService;

    @GetMapping({"/", "/home"})
    public String home(Model model) {
        Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>)SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        List<Animal> carouselAnimals = animalService.getRandom(6);
        model.addAttribute("firstCarouselAnimal", carouselAnimals.get(0));
        model.addAttribute("carouselAnimals", carouselAnimals.subList(1,5));
        model.addAttribute("randAnimals", carouselAnimals.subList(0,3));
        System.out.println(authorities);

        animalService.getRandom(4);
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
