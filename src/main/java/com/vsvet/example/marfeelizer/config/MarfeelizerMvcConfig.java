package com.vsvet.example.marfeelizer.config;

import com.vsvet.example.marfeelizer.exception.GlobalControllerExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Import(value = {SwaggerUIConfig.class, ControllerConfig.class})
@EnableWebMvc
@Configuration
public class MarfeelizerMvcConfig {

    @Bean
    public GlobalControllerExceptionHandler exceptionHandler() {
        return new GlobalControllerExceptionHandler();
    }
}
