package com.yearly.idol.api.yearly_idol.Common.annotation;

import com.yearly.idol.api.yearly_idol.Common.validator.PasswordValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = PasswordValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Password {
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };

    String regex() default "^(?=.*[a-z])(?=.*[A-Z]).{8,20}$"; // 전체
    String message() default "비밀번호 형식이 잘못되었습니다.";

    String regexpLength() default "^.{8,20}$";
    String messageLength() default "8-20자 사이로 입력하세요.";

    String regexpUpperCase() default ".*[A-Z].*";
    String messageUpperCase() default "대문자를 하나 이상 포함해야 합니다.";

    String regexpLowerCase() default ".*[a-z].*";
    String messageLowerCase() default "소문자를 하나 이상 포함해야 합니다.";
}
