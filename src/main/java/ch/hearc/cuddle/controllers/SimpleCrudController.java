package ch.hearc.cuddle.controllers;

import ch.hearc.cuddle.models.Breed;
import ch.hearc.cuddle.models.DatabaseEnum;
import ch.hearc.cuddle.models.Species;
import ch.hearc.cuddle.service.BreedService;
import ch.hearc.cuddle.service.SpeciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/dashboard")
public class SimpleCrudController {

    private final int ROW_PER_PAGE = 10;

    @Autowired
    private SpeciesService speciesService;

    @Autowired
    private BreedService breedService;

    @GetMapping(value = "/{type}")
    public String get(Model model, @PathVariable String type, @RequestParam(value = "page", defaultValue = "1") int pageNumber) {
        System.out.println("TYPE");
        if (wrongType(type))
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
        if (wrongType(type))
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
        if (wrongType(type))
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

        model.addAttribute("typeName", StringUtils.capitalize(type));
        model.addAttribute("type", type);
        model.addAttribute("add", true);
        model.addAttribute("enum", dbEnum);

        return "simple-crud/edit";
    }


    @PostMapping(value = "/{type}/add")
    public String add(Model model, @PathVariable String type, @ModelAttribute("dbEnum") DatabaseEnum newEnum) {
        if (wrongType(type))
            return "error";

        DatabaseEnum dbEnum;

        switch (type) {
            default:
            case "species":
                dbEnum = speciesService.save(speciesService.toSpecies(newEnum));
                break;
            case "breeds":
                dbEnum = breedService.save(breedService.toBreed(newEnum));
                break;
        }

        if(dbEnum != null)
            return "redirect:/dashboard/" + type + "/" + dbEnum.getId();

        model.addAttribute("typeName",StringUtils.capitalize(type));
        model.addAttribute("type", type);
        model.addAttribute("enum", newEnum);
        model.addAttribute("errorMessage", "Failed to add");
        model.addAttribute("add", true);

        return "simple-crud/edit";

    }


    @GetMapping(value = {"/{type}/{id}/edit"})
    public String showEdit(Model model, @PathVariable String type, @PathVariable long id) {
        if (wrongType(type))
            return "error";

        DatabaseEnum dbEnum;

        switch (type) {
            default:
            case "species":
                dbEnum = speciesService.findById(id);
                break;
            case "breeds":
                dbEnum = breedService.findById(id);
                break;
        }

        if (dbEnum == null)
            model.addAttribute("errorMessage", StringUtils.capitalize(type) + " not found");

        model.addAttribute("typeName", StringUtils.capitalize(type));
        model.addAttribute("type", type);
        model.addAttribute("add", false);
        model.addAttribute("enum", dbEnum);

        return "simple-crud/edit";
    }


    @PostMapping(value = {"/{type}/{id}/edit"})
    public String update(Model model, @PathVariable String type, @PathVariable long id, @ModelAttribute("dbEnum") DatabaseEnum dbEnum) {
        if (wrongType(type))
            return "error";

        dbEnum.setId(id);

        boolean ok;
        switch (type) {
            default:
            case "species":
                ok = speciesService.update(speciesService.toSpecies(dbEnum));
                break;
            case "breeds":
                ok = breedService.update(breedService.toBreed(dbEnum));
                break;
        }

        if (ok) {
            return "redirect:/dashboard/" + type + "/" + dbEnum.getId();
        }

        model.addAttribute("errorMessage", StringUtils.capitalize(type) + " not found");
        model.addAttribute("add", false);

        return "simple-crud/edit";
    }


    @GetMapping(value = {"/{type}/{id}/delete"})
    public String showDeleteById(Model model, @PathVariable String type, @PathVariable long id) {
        if (wrongType(type))
            return "error";

        DatabaseEnum dbEnum;

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
            model.addAttribute("errorMessage", "Not found");
        }
        else
        {
            model.addAttribute("enum", dbEnum);
        }

        model.addAttribute("typeName", StringUtils.capitalize(type));
        model.addAttribute("type", type);
        model.addAttribute("allowDelete", true);

        return "simple-crud/getById";
    }


    @PostMapping(value = {"/{type}/{id}/delete"})
    public String deleteById(Model model, @PathVariable String type, @PathVariable long id) {
        if (wrongType(type))
            return "error";

        boolean ok;
        DatabaseEnum dbEnum;

        switch (type) {
            default:
            case "species":
                dbEnum = speciesService.findById(id);
                ok = speciesService.deleteById(id);
                break;
            case "breeds":
                dbEnum = speciesService.findById(id);
                ok = breedService.deleteById(id);
                break;
        }

        System.out.println(id);

        if(ok)
            return "redirect:/dashboard/" + type;

        model.addAttribute("typeName", StringUtils.capitalize(type));
        model.addAttribute("type", type);
        model.addAttribute("enum", dbEnum);
        model.addAttribute("errorMessage", "Could not delete");
        return "simple-crud/getById";
    }

    private boolean wrongType(String type) {
        return !type.equals("species") && !type.equals("breeds");
    }
}