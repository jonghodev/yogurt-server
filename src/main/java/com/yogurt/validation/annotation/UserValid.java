package com.yogurt.validation.annotation;

import com.yogurt.validation.UserValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserValidator.class)
@Documented
public @interface UserValid {
    String message() default "존재하지 않는 유저입니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
