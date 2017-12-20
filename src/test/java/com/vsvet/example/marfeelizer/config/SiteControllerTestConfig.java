package com.vsvet.example.marfeelizer.config;

import com.vsvet.example.marfeelizer.controller.SiteController;
import com.vsvet.example.marfeelizer.service.SiteService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;
import org.springframework.web.method.annotation.RequestHeaderMapMethodArgumentResolver;
import org.springframework.web.method.annotation.RequestParamMethodArgumentResolver;

@Configuration
public class SiteControllerTestConfig {

    @Bean
    public MockMvc mockMvc() {
        StandaloneMockMvcBuilder builder = MockMvcBuilders.standaloneSetup(siteController());
        builder.setMessageConverters(converter());
        builder.setCustomArgumentResolvers(requestParamMethodArgumentResolver(),
                requestHeaderMapMethodArgumentResolver());
        return builder.build();
    }

    private MappingJackson2HttpMessageConverter converter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(ObjectMapperConfig.objectMapper());
        return converter;
    }

    @Bean
    public SiteController siteController() {
        return new SiteController(siteService());
    }

    @Bean
    public SiteService siteService() {
        return Mockito.mock(SiteService.class);
    }

    @Bean
    public RequestParamMethodArgumentResolver requestParamMethodArgumentResolver() {
        return new RequestParamMethodArgumentResolver(false);
    }

    @Bean
    public RequestHeaderMapMethodArgumentResolver requestHeaderMapMethodArgumentResolver() {
        return new RequestHeaderMapMethodArgumentResolver();
    }
}
