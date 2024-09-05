package com.example.rest_and_validation_exercise.validator;

import com.example.rest_and_validation_exercise.annotation.YearMonth;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
public class YearMonthValidator implements ConstraintValidator<YearMonth, String> {

    private String pattern;

    @Override
    public void initialize(YearMonth constraintAnnotation) {
        this.pattern = constraintAnnotation.pattern();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        var reValue = value + "01";
        var rePattern = pattern + "dd";
        try{
            LocalDate date = LocalDate.parse(reValue, DateTimeFormatter.ofPattern(rePattern));
            log.info("YearMonthValidator: {}", date);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

}
