package ch.hearc.cuddle.controllers;

import ch.hearc.cuddle.models.Breed;
import ch.hearc.cuddle.models.DatabaseEnum;
import ch.hearc.cuddle.models.Species;
import ch.hearc.cuddle.service.BreedService;
import ch.hearc.cuddle.service.SpeciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/dashboard")
public class SimpleCrudController {

    private final int ROW_PER_PAGE = 5;

    @Autowired
    private SpeciesService speciesService;

    @Autowired
    private BreedService breedService;


    @GetMapping(value = "/{type}")
    public String get(Model model, @PathVariable String type, @RequestParam(value = "page", defaultValue = "1") int pageNumber) {
        if (!checkType(type))
            return "error";

        List<? extends DatabaseEnum> enums;
        long count;

        switch (type) {
            default:
            case "species":
                enums = speciesService.findAll(pageNumber, ROW_PER_PAGE);
                count = speciesService.count();
                break;
            case "breeds":
                enums = breedService.findAll(pageNumber, ROW_PER_PAGE);
                count = breedService.count();
                break;
        }

        boolean hasPrev = pageNumber > 1;
        boolean hasNext = ((long) pageNumber * ROW_PER_PAGE) < count;

        model.addAttribute("typeName", StringUtils.capitalize(type));
        model.addAttribute("type", type);
        model.addAttribute("enums", enums);
        model.addAttribute("hasPrev", hasPrev);
        model.addAttribute("prev", pageNumber - 1);
        model.addAttribute("hasNext", hasNext);
        model.addAttribute("next", pageNumber + 1);

        return "simple-crud/get";
    }


    @GetMapping(value = "/{type}/{id}")
    public String getById(Model model, @PathVariable String type, @PathVariable long id) {
        if (!checkType(type))
            return "error";

        DatabaseEnum dbEnum = null;

        switch (type) {
            default:
            case "species":
                dbEnum = speciesService.findById(id);
                break;
            case "breeds":
                dbEnum = breedService.findById(id);
                break;
        }

        if (dbEnum == null) {
            model.addAttribute("errorMessage", StringUtils.capitalize(type) + " not found");
        }

        model.addAttribute("typeName", StringUtils.capitalize(type));
        model.addAttribute("type", type);
        model.addAttribute("enum", dbEnum);

        return "simple-crud/getById";
    }


    @GetMapping(value = {"/{type}/add"})
    public String showAdd(Model model, @PathVariable String type) {
        if (!checkType(type))
            return "error";


        DatabaseEnum dbEnum;

        switch (type) {
            default:
            case "species":
                dbEnum = new Species();
                break;
            case "breeds":
                dbEnum = new Breed();
                break;
        }

        model.addAttribute("add", true);
        model.addAttribute("contact", dbEnum);

        return "simple-crud/edit";
    }


    @PostMapping(value = "/{type}/add")
    public String add(Model model, @PathVariable String type, @ModelAttribute("dbEnum") DatabaseEnum newEnum) {
        if (!checkType(type))
            return "error";

        DatabaseEnum dbEnum;

        try {
            switch (type) {
                default:
                case "species":
                    dbEnum = speciesService.save(newEnum);
                    break;
                case "breeds":
                    dbEnum = breedService.save(newEnum);
                    break;
            }

            return "redirect:/" + type + "/" + dbEnum.getId();
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();

            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("add", true);

            return "contact-edit";
        }
    }


    @GetMapping(value = {"/{type}/{id}/edit"})
    public String showEdit(Model model, @PathVariable String type, @PathVariable long id) {
        if (!checkType(type))
            return "error";

        return "simple-crud/edit";
    }


    @PostMapping(value = {"/{type}/{id}/edit"})
    public String update(Model model, @PathVariable String type, @PathVariable long id,
                         @ModelAttribute("dbEnum") DatabaseEnum dbEnum) {
        if (!checkType(type))
            return "error";

        return "simple-crud/edit";
    }


    @GetMapping(value = {"/{type}/{id}/delete"})
    public String showDeleteById(Model model, @PathVariable String type, @PathVariable long id) {
        if (!checkType(type))
            return "error";

        return "";
    }


    @PostMapping(value = {"/{type}/{id}/delete"})
    public String deleteById(Model model, @PathVariable String type, @PathVariable long id) {
        if (!checkType(type))
            return "error";

        return "";
    }

    private boolean checkType(String type) {
        return type.equals("species") || type.equals("breeds");
    }
}