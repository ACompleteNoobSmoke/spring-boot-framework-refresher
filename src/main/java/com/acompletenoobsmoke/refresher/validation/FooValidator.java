package com.acompletenoobsmoke.refresher.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FooValidator implements ConstraintValidator<Foo, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return value == null || value.equals("FOO");
    }
}
