package com.yearly.idol.api.yearly_idol.Common.validator;

import com.yearly.idol.api.yearly_idol.Common.annotation.Password;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Pattern;

@Slf4j
public class PasswordValidator implements ConstraintValidator<Password, String> {

    private Password con;

    @Override
    public void initialize(Password constraintAnnotation) {
        // ConstraintValidator.super.initialize(constraintAnnotation);
        this.con = constraintAnnotation;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext ctx) {
        log.info("VALIDATION value: {}", value);
        boolean isValid = true;

        ctx.disableDefaultConstraintViolation(); // 기본 메시지 비활성화

        if (!Pattern.compile(con.regex()).matcher(value).matches()) {
            ctx.buildConstraintViolationWithTemplate(con.message()).addConstraintViolation();
            isValid = false;
        }

        if (!Pattern.compile(con.regexpLength()).matcher(value).matches()) {
            ctx.buildConstraintViolationWithTemplate(con.messageLength()).addConstraintViolation();
            isValid = false;
        }

        if (!Pattern.compile(con.regexpUpperCase()).matcher(value).matches()) {
            ctx.buildConstraintViolationWithTemplate(con.messageUpperCase()).addConstraintViolation();
            isValid = false;
        }

        if (!Pattern.compile(con.regexpLowerCase()).matcher(value).matches()) {
            ctx.buildConstraintViolationWithTemplate(con.messageLowerCase()).addConstraintViolation();
            isValid = false;
        }

        return isValid;
    }
}
