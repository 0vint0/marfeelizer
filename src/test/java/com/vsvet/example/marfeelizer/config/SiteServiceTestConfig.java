package com.vsvet.example.marfeelizer.config;

import com.vsvet.example.marfeelizer.repository.MarfeelizingCriteriaRepository;
import com.vsvet.example.marfeelizer.repository.SiteRepository;
import com.vsvet.example.marfeelizer.service.MarfeelizeService;
import com.vsvet.example.marfeelizer.service.SiteService;
import com.vsvet.example.marfeelizer.service.impl.SiteServiceImpl;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SiteServiceTestConfig {

    @Bean
    public SiteService siteService() {
        return new SiteServiceImpl(marfeelizeService(), siteRepository(), marfeelizingCriteriaRepository());
    }

    @Bean
    public MarfeelizeService marfeelizeService() {
        return Mockito.mock(MarfeelizeService.class);
    }

    @Bean
    public SiteRepository siteRepository() {
        return Mockito.mock(SiteRepository.class);
    }

    @Bean
    public MarfeelizingCriteriaRepository marfeelizingCriteriaRepository() {
        return Mockito.mock(MarfeelizingCriteriaRepository.class);
    }

}
