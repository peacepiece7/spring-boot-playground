package com.example.rest_and_validation_exercise.annotation;

import com.example.rest_and_validation_exercise.validator.KoreanOnlyValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = { KoreanOnlyValidator.class})
public @interface KoreanOnly {
    String message() default "한글만 입력할 수 있습니다.";

    String regex() default "[가-힣]+";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}