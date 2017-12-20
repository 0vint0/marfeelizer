package com.vsvet.example.marfeelizer.config;

import com.vsvet.example.marfeelizer.repository.MarfeelizingCriteriaRepository;
import com.vsvet.example.marfeelizer.repository.SiteRepository;
import com.vsvet.example.marfeelizer.service.MarfeelizeService;
import com.vsvet.example.marfeelizer.service.MarfeelizerCheckerFactory;
import com.vsvet.example.marfeelizer.service.SiteService;
import com.vsvet.example.marfeelizer.service.impl.MarfeelizeServiceImpl;
import com.vsvet.example.marfeelizer.service.impl.SiteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Import(value = PersistenceConfig.class)
@Configuration
@EnableAsync
public class ServiceConfig {

    @Autowired
    private SiteRepository siteRepository;
    @Autowired
    private MarfeelizingCriteriaRepository marfeelizingCriteriaRepository;

    @Bean
    public SiteService siteService() {
        return new SiteServiceImpl(marfeelizeService(), siteRepository,marfeelizingCriteriaRepository);
    }

    @Bean
    public MarfeelizeService marfeelizeService() {
        return new MarfeelizeServiceImpl(marfeelizerCheckerFactory());
    }

    @Bean
    public MarfeelizerCheckerFactory marfeelizerCheckerFactory(){
        return new MarfeelizerCheckerFactory();
    }

    @Bean
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4);
        executor.setMaxPoolSize(4);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("Marfeelization-Thread-");
        executor.initialize();
        return executor;
    }
}
