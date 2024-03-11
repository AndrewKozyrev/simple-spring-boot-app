package com.example.demo.model;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidAgeValidator.class)
public @interface ValidAge {

    String message() default "Age must be between {min} and {max} years old";

    int min() default 1;

    int max() default 120;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}