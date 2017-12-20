package com.vsvet.example.marfeelizer.initializer;

import com.vsvet.example.marfeelizer.config.MarfeelizerAppConfig;
import com.vsvet.example.marfeelizer.config.MarfeelizerMvcConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import javax.servlet.ServletRegistration;

public class MarfeelizerInitializer implements WebApplicationInitializer {

    public void onStartup(javax.servlet.ServletContext servletContext) {
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(MarfeelizerAppConfig.class);
        ContextLoaderListener contextLoaderListener = new ContextLoaderListener(rootContext);
        servletContext.addListener(contextLoaderListener);

        ServletRegistration.Dynamic servletConfig = servletContext.addServlet("appServlet", DispatcherServlet.class);
        servletConfig.setInitParameter("contextClass", AnnotationConfigWebApplicationContext.class.getName());
        servletConfig.setInitParameter("contextConfigLocation", MarfeelizerMvcConfig.class.getName());
        servletConfig.addMapping("/");
        servletConfig.setLoadOnStartup(1);
    }
}
