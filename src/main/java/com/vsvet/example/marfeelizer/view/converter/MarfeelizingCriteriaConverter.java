package com.vsvet.example.marfeelizer.view.converter;

import com.google.common.base.Converter;
import com.vsvet.example.marfeelizer.domain.MarfeelizingCriteria;
import com.vsvet.example.marfeelizer.view.MarfeelizingCriteriaView;

public class MarfeelizingCriteriaConverter extends Converter<MarfeelizingCriteria, MarfeelizingCriteriaView> {

    @Override
    protected MarfeelizingCriteriaView doForward(MarfeelizingCriteria marfeelizingCriteria) {
        MarfeelizingCriteriaView view = new MarfeelizingCriteriaView();
        view.setName(marfeelizingCriteria.getName());
        view.setType(marfeelizingCriteria.getType().name());
        view.setProperties(
                marfeelizingCriteria.getProperties()
        );
        return view;
    }

    @Override
    protected MarfeelizingCriteria doBackward(MarfeelizingCriteriaView marfeelizingCriteriaView) {
        throw new UnsupportedOperationException();// not supported at now.
    }
}
