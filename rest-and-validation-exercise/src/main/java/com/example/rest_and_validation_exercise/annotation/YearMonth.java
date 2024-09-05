package com.example.rest_and_validation_exercise.annotation;


import com.example.rest_and_validation_exercise.validator.PastOrPresentYearValidator;
import com.example.rest_and_validation_exercise.validator.YearMonthValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = YearMonthValidator.class)
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@NotBlank
public @interface YearMonth {
    String message() default "엔트로피가 역전되셨나요?";

    String pattern() default "yyyyMM";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
