package com.vsvet.example.marfeelizer.service.impl;

import com.vsvet.example.marfeelizer.domain.MarfeelizingCriteria;
import com.vsvet.example.marfeelizer.service.AbstractTest;
import com.vsvet.example.marfeelizer.service.MarfeelizeService;
import com.vsvet.example.marfeelizer.service.MarfeelizerChecker;
import com.vsvet.example.marfeelizer.service.MarfeelizerCheckerFactory;
import com.vsvet.example.marfeelizer.config.MarfeelizeServiceTestConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static com.vsvet.example.marfeelizer.util.BeanTestUtils.URL_1;
import static com.vsvet.example.marfeelizer.util.BeanTestUtils.URL_2;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;


@ContextConfiguration(classes = {MarfeelizeServiceTestConfig.class})
public class MarfeelizeServiceImplTest extends AbstractTest {

    @Autowired
    private MarfeelizerCheckerFactory marfeelizerCheckerFactory;
    @Mock
    private MarfeelizingCriteria criteria;
    @Mock
    private MarfeelizerChecker marfeelizerChecker;

    @Autowired
    private MarfeelizeService toTest;

    @Before
    public void setup() throws IOException {
        MockitoAnnotations.initMocks(this);
        prepareMarfeelizerCheckerFactory();
    }

    @After
    public void cleanup() {
        Mockito.reset(marfeelizerCheckerFactory);
    }

    @Test
    public void testMarfeelizingMarfeelizableUrl() throws ExecutionException, InterruptedException {
        CompletableFuture<Boolean> completableFuture = toTest.checkCriteria(URL_1, criteria);
        assertTrue(completableFuture.get());
    }

    @Test
    public void testMarfeelizingNotMarfeelizableUrl() throws ExecutionException, InterruptedException {
        CompletableFuture<Boolean> completableFuture = toTest.checkCriteria(URL_2, criteria);
        assertFalse(completableFuture.get());
    }


    private void prepareMarfeelizerCheckerFactory() throws IOException {
        when(marfeelizerCheckerFactory.get(criteria)).thenReturn(marfeelizerChecker);
        when(marfeelizerChecker.marfeelizable(URL_1)).thenReturn(true);
        when(marfeelizerChecker.marfeelizable(URL_2)).thenReturn(false);

    }

}
