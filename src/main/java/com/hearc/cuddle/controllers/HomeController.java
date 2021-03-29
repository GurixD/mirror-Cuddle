package com.hearc.cuddle.controllers;

import com.hearc.cuddle.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    UserRepository userRepository;

    @Autowired

    @GetMapping("/home")
    public String hello() {
        return "home";
    }

}
