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
    String message() default "엔트로피가 역전되셨나요?";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
