package com.vsvet.example.marfeelizer.service;

import com.vsvet.example.marfeelizer.domain.MarfeelizingCriteria;
import com.vsvet.example.marfeelizer.service.impl.TitleContainsMarfeelizerChecker;

public class MarfeelizerCheckerFactory {

    public MarfeelizerChecker get(MarfeelizingCriteria criteria) {
        switch (criteria.getType()) {
            case CONTAINS_TITLE:
                return new TitleContainsMarfeelizerChecker(criteria);
            default:
                throw new RuntimeException("Undefined criteria type " + criteria.getType());
        }
    }
}
