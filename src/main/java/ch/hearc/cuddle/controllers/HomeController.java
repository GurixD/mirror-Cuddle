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
        List<Animal> carouselAnimals = animalService.getRandom(7);
        model.addAttribute("firstCarouselAnimal", carouselAnimals.get(0));
        model.addAttribute("carouselAnimals", carouselAnimals.subList(1,6));
        model.addAttribute("randAnimals", carouselAnimals.subList(0,6));
        System.out.println(authorities);

        animalService.getRandom(4);
        return "home";
    }
}
