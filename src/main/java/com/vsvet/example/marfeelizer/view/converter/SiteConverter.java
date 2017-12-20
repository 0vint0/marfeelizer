package com.vsvet.example.marfeelizer.view.converter;

import com.google.common.base.Converter;
import com.vsvet.example.marfeelizer.domain.Site;
import com.vsvet.example.marfeelizer.view.MarfeelizingStatusView;
import com.vsvet.example.marfeelizer.view.SiteView;

import java.util.stream.Collectors;

public class SiteConverter  extends Converter<Site,SiteView>{

    private MarfeelizingCriteriaConverter criteriaConverter = new MarfeelizingCriteriaConverter();

    @Override
    protected SiteView doForward(Site site) {
        SiteView view  = new SiteView();
        view.setStatus(MarfeelizingStatusView.valueOf(site.getStatus().name()));
        view.setUrl(site.getUrl());
        view.setCreatedDate(site.getCreatedDate());
        view.setModifiedDate(site.getModifiedDate());
        view.setCriteriaViews(site.getCriterias().stream().map(criteriaConverter::convert).collect(Collectors.toList()));
        return view;
    }

    @Override
    protected Site doBackward(SiteView siteView) {
        Site site = new Site();
        site.setUrl(siteView.getUrl());
        return site;
    }
}
