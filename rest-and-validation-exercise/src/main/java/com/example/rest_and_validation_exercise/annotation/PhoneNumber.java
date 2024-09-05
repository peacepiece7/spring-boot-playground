package com.example.rest_and_validation_exercise.annotation;

import com.example.rest_and_validation_exercise.validator.PhoneNumberValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 어노테이션 @NotNull 에 들어가보면 어떤걸 구현해야하는지 나옴
 */
@Constraint(validatedBy = { PhoneNumberValidator.class}) // validator 지정
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneNumber {
    String message() default "핸드폰 번호 양식에 맞지 않습니다. ex) 000-0000-0000";

    String regexp() default "^01[0-9]-\\d{3,4}-\\d{4}$";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
