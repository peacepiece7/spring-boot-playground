package com.example.rest_and_validation_exercise.validator;

import com.example.rest_and_validation_exercise.annotation.PastOrPresentYear;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.Year;
import java.util.Objects;
import java.util.regex.Pattern;

public class PastOrPresentYearValidator implements ConstraintValidator<PastOrPresentYear, Integer> {

    @Override
    public void initialize(PastOrPresentYear constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer year, ConstraintValidatorContext context) {
        int currentYear = Year.now().getValue();
        return year <= currentYear;
    }
}

