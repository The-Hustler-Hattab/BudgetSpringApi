package com.mtattab.emailservice.validatior;

import com.mtattab.emailservice.annotations.GreaterThanValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class GreaterThanValidators implements ConstraintValidator<GreaterThanValidator, Long> {

    private int greaterThanValue;

    @Override
    public void initialize(GreaterThanValidator constraintAnnotation) {
        greaterThanValue = constraintAnnotation.value();
    }
    @Override
    public boolean isValid(Long aLong, ConstraintValidatorContext constraintValidatorContext) {
        return aLong >= greaterThanValue;
    }
}
