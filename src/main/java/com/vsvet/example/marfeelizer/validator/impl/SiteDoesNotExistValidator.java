package com.vsvet.example.marfeelizer.validator.impl;

import com.vsvet.example.marfeelizer.repository.SiteRepository;
import com.vsvet.example.marfeelizer.validator.AbstractConstraintValidator;
import com.vsvet.example.marfeelizer.validator.NotNullOrBlank;
import com.vsvet.example.marfeelizer.validator.SiteDoesNotExist;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.MessageFormat;

public class SiteDoesNotExistValidator extends AbstractConstraintValidator<SiteDoesNotExist, String> {

    private SiteDoesNotExist notBlank;

    @Autowired
    private SiteRepository siteRepository;

    @Override
    public void initialize(SiteDoesNotExist notBlank) {
        this.notBlank = notBlank;
    }


    @Override
    protected boolean isValid(String url) {
        return url == null || !siteRepository.findByUrl(url).isPresent();
    }

    @Override
    protected String getValidationMessage(String url) {
        return MessageFormat.format(notBlank.message(), url);
    }


}
