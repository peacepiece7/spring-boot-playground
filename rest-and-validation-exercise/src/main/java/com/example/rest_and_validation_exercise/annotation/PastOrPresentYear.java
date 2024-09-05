package com.example.rest_and_validation_exercise.annotation;


import com.example.rest_and_validation_exercise.validator.PastOrPresentYearValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PastOrPresentYearValidator.class)
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface PastOrPresentYear {
    String message() default "미래에 태어날 수 있어요?";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
