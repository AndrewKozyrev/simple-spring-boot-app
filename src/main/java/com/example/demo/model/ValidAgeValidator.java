package com.example.demo.model;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidAgeValidator implements ConstraintValidator<ValidAge, Integer> {

    private int min;
    private int max;

    @Override
    public void initialize(ValidAge constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        boolean isValid = value >= min && value <= max;
        if (!isValid) {
            String messageTemplate = context.getDefaultConstraintMessageTemplate();
            String message = messageTemplate.replace("{min}", String.valueOf(min))
                    .replace("{max}", String.valueOf(max));
            context.buildConstraintViolationWithTemplate(message)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
        }
        return isValid;
    }
}