package com.example.demoswagger.Module;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ResourceDateTimeValidator implements ConstraintValidator<ResourceDateTimeValid, String> {

    private String dateFormat;

    @Override
    public void initialize(ResourceDateTimeValid constraintAnnotation) {
        dateFormat = constraintAnnotation.fomart();
    }

    @Override
    public boolean isValid(String strDate, ConstraintValidatorContext context) {
        try {
            DateFormat strDateFormat = new SimpleDateFormat(this.dateFormat);
            strDateFormat.setLenient(false);
            strDateFormat.parse(strDate);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}