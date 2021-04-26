package ch.hearc.cuddle.validator;

import ch.hearc.cuddle.models.Animal;
import ch.hearc.cuddle.models.DatabaseEnum;
import ch.hearc.cuddle.models.Species;
import ch.hearc.cuddle.service.SpeciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class SpeciesValidator implements Validator {
    @Autowired
    private SpeciesService speciesService;



    @Override
    public boolean supports(Class<?> aClass) {
        return Animal.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Species species = speciesService.toSpecies((DatabaseEnum) o);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty", "Name is empty");
        if (!speciesService.findByName(species.getName()).isEmpty()) {
            errors.rejectValue("name", "Duplicate.speciesForm.name", "Species duplicate");
        }
    }
}
