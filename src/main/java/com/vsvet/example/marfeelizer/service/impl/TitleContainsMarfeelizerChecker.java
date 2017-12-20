package com.vsvet.example.marfeelizer.service.impl;

import com.vsvet.example.marfeelizer.domain.MarfeelizingCriteria;
import com.vsvet.example.marfeelizer.service.AbstractMarfeelizerChecker;
import org.jsoup.nodes.Document;

/**
 * Defines logic to check Marfeelization by website title content.
 * In the same way this can be extended to be used for another criterias.
 */
public class TitleContainsMarfeelizerChecker extends AbstractMarfeelizerChecker {

    private static final String TITLE_PROPERTY_NAME = "TITLE";

    public TitleContainsMarfeelizerChecker(MarfeelizingCriteria criteria) {
        super(criteria);
    }

    @Override
    protected boolean checkDocument(Document document) {
        String textToCheck = getCriteria().getProperties().get(TITLE_PROPERTY_NAME);
        return document.title().contains(textToCheck);
    }


}
