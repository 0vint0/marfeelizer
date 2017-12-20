package com.vsvet.example.marfeelizer.config;

import com.vsvet.example.marfeelizer.repository.MarkerJPADomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.sql.SQLException;

@Import(value = DBConfig.class)
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackageClasses = MarkerJPADomainRepository.class)
public class PersistenceConfig {

    private static final String PACKAGE_TO_SCAN = "com.vsvet.example.marfeelizer.domain";

    private static final String PERSISTENCE_UNIT_NAME = "marfeelizer";

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JpaVendorAdapter jpaVendorAdapter;

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setDataSource(dataSource);
        bean.setJpaVendorAdapter(jpaVendorAdapter);
        bean.setPackagesToScan(PACKAGE_TO_SCAN);
        bean.setPersistenceUnitName(PERSISTENCE_UNIT_NAME);
        bean.afterPropertiesSet();
        return bean.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager(entityManagerFactory());
    }

}
