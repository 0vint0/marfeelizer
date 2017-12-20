package com.vsvet.example.marfeelizer.util;

import com.vsvet.example.marfeelizer.domain.MarfeelizingCriteria;
import com.vsvet.example.marfeelizer.domain.MarfeelizingCriteriaType;
import com.vsvet.example.marfeelizer.domain.MarfeelizingStatus;
import com.vsvet.example.marfeelizer.domain.Site;
import com.vsvet.example.marfeelizer.view.SiteView;

public class BeanTestUtils {

    public static final String URL_1 = "http://www.marfeelizedByCriteria1.com";
    public static final String URL_2 = "http://www.marfeelizedByCriteria2.com";
    public static final String URL_3 = "http://www.noMarfeelized.com";

    public static final MarfeelizingStatus STATUS_1 = MarfeelizingStatus.MARFEELIZABLE;
    public static final MarfeelizingStatus STATUS_2 = MarfeelizingStatus.MARFEELIZABLE;
    public static final MarfeelizingStatus STATUS_3 = MarfeelizingStatus.UNMARFEELIZABLE;

    public static final String CRITERIA_NAME = "Title must contains sth";
    public static final MarfeelizingCriteriaType CRITERIA_TYPE = MarfeelizingCriteriaType.CONTAINS_TITLE;
    public static final String CRITERIA_PROPERTY_NAME = "TITLE";
    public static final String CRITERIA_PROPERTY_VALUE = "Value1";


    public static Site site1() {
        Site site = new Site();
        site.setUrl(URL_1);
        site.setStatus(STATUS_1);
        return site;
    }

    public static Site site2() {
        Site site = new Site();
        site.setUrl(URL_2);
        site.setStatus(STATUS_2);
        return site;
    }

    public static Site site3() {
        Site site = new Site();
        site.setUrl(URL_3);
        site.setStatus(STATUS_3);
        return site;
    }

    public static SiteView siteView1() {
        SiteView view = new SiteView();
        view.setUrl(URL_1);
        return view;
    }

    public static SiteView siteView2() {
        SiteView view = new SiteView();
        view.setUrl(URL_2);
        return view;
    }

    public static SiteView siteView3() {
        SiteView view = new SiteView();
        view.setUrl(URL_3);
        return view;
    }

    public static MarfeelizingCriteria criteria() {
        MarfeelizingCriteria criteria = new MarfeelizingCriteria();
        criteria.setName(CRITERIA_NAME);
        criteria.setType(CRITERIA_TYPE);
        criteria.getProperties().put(CRITERIA_PROPERTY_NAME, CRITERIA_PROPERTY_VALUE);
        return criteria;
    }

}
