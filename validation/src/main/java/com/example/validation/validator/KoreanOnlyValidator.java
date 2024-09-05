package com.example.validation.validator;


import com.example.validation.annotation.KoreanOnly;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class KoreanOnlyValidator implements ConstraintValidator<KoreanOnly, String> {
    private String regexp;

    // isValid 어노테이션이 호출되면 이게 먼저 실행됨
    @Override
    public void initialize(KoreanOnly constraintAnnotation) {
        this.regexp = constraintAnnotation.regex();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        //
        return Pattern.matches(regexp, value);
    };
}
