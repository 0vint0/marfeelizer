package com.vsvet.example.marfeelizer.config;

import com.vsvet.example.marfeelizer.domain.MarfeelizingCriteria;
import com.vsvet.example.marfeelizer.domain.MarfeelizingCriteriaType;
import com.vsvet.example.marfeelizer.repository.MarfeelizingCriteriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.SQLException;

@Import(value = {ServiceConfig.class,ValidationConfig.class})
@Configuration
@EnableAsync
public class MarfeelizerAppConfig {

    @Autowired
    private MarfeelizingCriteriaRepository marfeelizingCriteriaRepository;

    //Usually it will be done by DB version controls something like liquibase.
    @PostConstruct
    @Transactional
    public void loadInitiatedData() throws SQLException {
        MarfeelizingCriteria criteria1 = new MarfeelizingCriteria();
        criteria1.setName("Title contains 'news'");
        criteria1.setType(MarfeelizingCriteriaType.CONTAINS_TITLE);
        criteria1.getProperties().put("TITLE", "news");

        MarfeelizingCriteria criteria2 = new MarfeelizingCriteria();
        criteria2.setName("Title contains 'noticias'");
        criteria2.setType(MarfeelizingCriteriaType.CONTAINS_TITLE);
        criteria2.getProperties().put("TITLE", "noticias");

        marfeelizingCriteriaRepository.save(criteria1);
        marfeelizingCriteriaRepository.save(criteria2);
    }
}
