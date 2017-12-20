package com.vsvet.example.marfeelizer.service;

import com.vsvet.example.marfeelizer.domain.MarfeelizingCriteria;

import java.io.IOException;

/**
 * Is used to define criteria by which site should be considered Marfeelizeble or not.
 */
public interface MarfeelizerChecker {

    boolean marfeelizable(String url) throws IOException;

    MarfeelizingCriteria getCriteria();
}
