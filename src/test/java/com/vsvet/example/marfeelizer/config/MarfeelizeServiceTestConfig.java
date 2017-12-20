package com.vsvet.example.marfeelizer.config;

import com.vsvet.example.marfeelizer.service.MarfeelizeService;
import com.vsvet.example.marfeelizer.service.MarfeelizerCheckerFactory;
import com.vsvet.example.marfeelizer.service.impl.MarfeelizeServiceImpl;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MarfeelizeServiceTestConfig {

    @Bean
    public MarfeelizeService marfeelizeService() {
        return new MarfeelizeServiceImpl(marfeelizerCheckerFactory());
    }

    @Bean
    public MarfeelizerCheckerFactory marfeelizerCheckerFactory() {
        return Mockito.mock(MarfeelizerCheckerFactory.class);
    }

}
