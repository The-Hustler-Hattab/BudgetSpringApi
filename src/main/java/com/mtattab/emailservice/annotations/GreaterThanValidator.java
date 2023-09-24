package com.mtattab.emailservice.annotations;

import com.mtattab.emailservice.validatior.GreaterThanValidators;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = GreaterThanValidators.class)
@Target({ FIELD })
@Retention(RUNTIME)
public  @interface GreaterThanValidator {

    String message() default "The field number must be at least {value} or greater.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    int value() default 1; // Minimum length (default is 1 character)
}
