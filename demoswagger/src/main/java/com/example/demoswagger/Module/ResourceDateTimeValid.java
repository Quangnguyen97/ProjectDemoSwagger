package com.example.demoswagger.Module;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = ResourceDateTimeValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ResourceDateTimeValid {

    public String message() default "Invalid datetime!";

    public String fomart() default "yyyy/dd/MM";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};
}
