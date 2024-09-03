package ru.skillaurora.articleservice.validate;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {TagsValidator.class})
public @interface Tags {
    String message() default "Id тегов должны быть больше нуля.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
