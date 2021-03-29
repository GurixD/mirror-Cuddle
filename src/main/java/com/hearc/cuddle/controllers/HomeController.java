package com.hearc.cuddle.controllers;

import com.hearc.cuddle.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    UserRepository userRepository;

//    @Autowired

    @GetMapping({"/", "/home"})
    public String home(Model model) {
        return "home";
    }

    @GetMapping({"/hello"})
    public String hello(Model model) {
        return "hello";
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
