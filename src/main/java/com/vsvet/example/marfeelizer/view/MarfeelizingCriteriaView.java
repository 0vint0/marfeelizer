package com.vsvet.example.marfeelizer.view;

import java.util.Map;

public class MarfeelizingCriteriaView {

    private String name;

    private String type;

    private Map<String,String> properties;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }
}
