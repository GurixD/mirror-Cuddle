package ch.hearc.cuddle.validator;

import ch.hearc.cuddle.models.Animal;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class AnimalValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Animal.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty", "Name is empty");
        ValidationUtils.rejectIfEmpty(errors, "age", "NotNull", "Age is empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sex", "NotEmpty", "Sex is empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "species", "NotEmpty", "Species is empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "breed", "NotEmpty", "Breed is empty");
    }
}
