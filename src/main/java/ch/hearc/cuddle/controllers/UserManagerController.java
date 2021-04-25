package ch.hearc.cuddle.controllers;

import ch.hearc.cuddle.auth.model.Role;
import ch.hearc.cuddle.auth.model.User;
import ch.hearc.cuddle.auth.service.RoleService;
import ch.hearc.cuddle.auth.service.UserService;
import ch.hearc.cuddle.auth.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/dashboard/users")
public class UserManagerController {

    private final int ROW_PER_PAGE = 10;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping(value = "")
    public String get(Model model, @RequestParam(value = "page", defaultValue = "1") int pageNumber) {
        List<User> users = userService.findAll(pageNumber, ROW_PER_PAGE);
        long count = userService.count();

        boolean hasPrev = pageNumber > 1;
        boolean hasNext = ((long) pageNumber * ROW_PER_PAGE) < count;
        model.addAttribute("users", users);
        model.addAttribute("hasPrev", hasPrev);
        model.addAttribute("prev", pageNumber - 1);
        model.addAttribute("hasNext", hasNext);
        model.addAttribute("next", pageNumber + 1);

        return "users/get";
    }


    @GetMapping(value = "/{id}")
    public String getById(Model model, @PathVariable long id) {
        User user = userService.findById(id);

        if (user == null) {
            model.addAttribute("errorMessage", "User not found");
        }
        model.addAttribute("user", user);

        return "users/getById";
    }


    @GetMapping(value = {"/add"})
    public String showAdd(Model model) {
        User user = new User();
        List<Role> roles = roleService.findAll();

        model.addAttribute("add", true);
        model.addAttribute("user", user);
        model.addAttribute("roles", roles);


        return "users/edit";
    }


    @PostMapping(value = "/add")
    public String add(Model model, @ModelAttribute("user") User newUser, BindingResult errors) throws IOException {
        List<Role> roles = roleService.findAll();
        
        model.addAttribute("user", newUser);
        model.addAttribute("add", true);
        model.addAttribute("roles", roles);


        userValidator.validate(newUser, errors);

        if (!errors.hasErrors()) {
            User user = userService.save(newUser);

            if (user != null) {
                return "redirect:/dashboard/users/" + user.getId();
            } else
                errors.addError(new ObjectError("user", "Failed to save user"));
        }

        StringBuilder errorsString = new StringBuilder();
        for (ObjectError er : errors.getAllErrors()) {
            errorsString.append(er.getDefaultMessage()).append("<br>");
        }

        model.addAttribute("errorMessage", errorsString.toString());

        return "users/edit";
    }


    @GetMapping(value = {"/{id}/edit"})
    public String showEdit(Model model, @PathVariable long id) {
        User user = userService.findById(id);
        List<Role> roles = roleService.findAll();


        if (user == null)
            model.addAttribute("errorMessage", "User not found");
        else
            user.setPassword("");

        model.addAttribute("add", false);
        model.addAttribute("user", user);
        model.addAttribute("roles", roles);


        return "users/edit";
    }


    @PostMapping(value = {"/{id}/edit"})
    public String update(Model model, @PathVariable long id, @ModelAttribute("user") User newUser, BindingResult errors) throws IOException {
        User oldUser = userService.findById(id);
        newUser.setId(id);
        

        List<Role> roles = roleService.findAll();


        model.addAttribute("user", newUser);
        model.addAttribute("add", false);
        model.addAttribute("roles", roles);


        userValidator.validateUpdate(newUser, errors);

        if (!errors.hasErrors()) {
            boolean encrypt = true;
            if(newUser.getPassword().isEmpty()){
                encrypt = false;
                newUser.setPassword(oldUser.getPassword());
            }
            User user = userService.save(newUser, encrypt);

            if (user != null) {
                return "redirect:/dashboard/users/" + user.getId();
            } else
                errors.addError(new ObjectError("user", "Failed to save user"));
        }

        StringBuilder errorsString = new StringBuilder();
        for (ObjectError er : errors.getAllErrors()) {
            errorsString.append(er.getDefaultMessage()).append("<br>");
        }

        model.addAttribute("errorMessage", errorsString.toString());

        return "users/edit";
    }


    @GetMapping(value = {"/{id}/delete"})
    public String showDeleteById(Model model, @PathVariable long id) {
        User user = userService.findById(id);

        if (user == null) {
            model.addAttribute("errorMessage", "User not found");
        } else {
            model.addAttribute("user", user);
        }

        model.addAttribute("allowDelete", true);

        return "users/getById";
    }



    @PostMapping(value = {"/{id}/delete"})
    public String deleteById(Model model, @PathVariable long id) {
        User user = userService.findById(id);
        boolean ok = userService.deleteById(id);

        if (ok)
            return "redirect:/dashboard/users";

        model.addAttribute("user", user);
        model.addAttribute("errorMessage", "Could not delete");

        return "users/getById";
    }

}
