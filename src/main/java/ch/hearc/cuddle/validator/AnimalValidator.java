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
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty");
        ValidationUtils.rejectIfEmpty(errors, "age", "NotNull");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sex", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "image", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "species", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "breed", "NotEmpty");
    }
}
