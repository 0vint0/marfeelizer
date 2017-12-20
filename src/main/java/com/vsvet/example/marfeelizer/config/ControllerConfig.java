package com.vsvet.example.marfeelizer.config;

import com.vsvet.example.marfeelizer.controller.SiteController;
import com.vsvet.example.marfeelizer.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllerConfig {

    @Autowired
    private SiteService siteService;

    @Bean
    public SiteController siteController(){
        return new SiteController(siteService);
    }
}
