package com.hearc.cuddle.controllers;

import com.hearc.cuddle.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DetailsController {

    @Autowired
    UserRepository userRepository;


    @GetMapping({"/details/{id}"})
    public String home(@PathVariable("id")String id) {
        return "details";
    }


}
