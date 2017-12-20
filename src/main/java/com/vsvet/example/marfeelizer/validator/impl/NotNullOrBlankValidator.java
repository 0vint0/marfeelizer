package com.vsvet.example.marfeelizer.validator.impl;

import com.vsvet.example.marfeelizer.validator.AbstractConstraintValidator;
import com.vsvet.example.marfeelizer.validator.NotNullOrBlank;

import java.text.MessageFormat;

public class NotNullOrBlankValidator extends AbstractConstraintValidator<NotNullOrBlank, String> {


    private NotNullOrBlank notBlank;

    @Override
    public void initialize(NotNullOrBlank notBlank) {
        this.notBlank = notBlank;
    }


    @Override
    protected boolean isValid(String value) {
        return value != null && !value.isEmpty();
    }

    @Override
    protected String getValidationMessage(String value) {
        return MessageFormat.format(notBlank.message(), notBlank.fieldName());
    }


}
