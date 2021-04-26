package ch.hearc.cuddle.validator;

import ch.hearc.cuddle.models.Animal;
import ch.hearc.cuddle.models.Breed;
import ch.hearc.cuddle.models.DatabaseEnum;
import ch.hearc.cuddle.service.BreedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class BreedValidator implements Validator {

    @Autowired
    private BreedService breedService;


    @Override
    public boolean supports(Class<?> aClass) {
        return Animal.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Breed breed = breedService.toBreed((DatabaseEnum) o);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty", "Name is empty");
        if (!breedService.findByName(breed.getName()).isEmpty()) {
            errors.rejectValue("name", "Duplicate.speciesForm.name", "Breed duplicate");
        }
    }
}
