package com.vsvet.example.marfeelizer.service.impl;

import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

import static com.vsvet.example.marfeelizer.util.BeanTestUtils.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TitleContainsMarfeelizerCheckerTest {

    private static final String NOT_MARFEELIZABLE_TITLE = "Aaadasdsada some";
    private static final String MARFEELIZABLE_TITLE = "Aaadasdsada some " + CRITERIA_PROPERTY_VALUE;

    @Mock
    private Document document;

    private TitleContainsMarfeelizerChecker toTest;

    @Before
    public void setup() {
        toTest = new TitleContainsMarfeelizerChecker(criteria()) {
            //Not good practice to override testing class, but because of jSoup API limitation
            // and for faster test will proceed in this way.
            @Override
            protected Document getDocument(String url) throws IOException {
                return document;
            }
        };
    }

    @Test
    public void testMarfeelizableSiteUrl() throws IOException {
        when(document.title()).thenReturn(MARFEELIZABLE_TITLE);
        assertTrue(toTest.marfeelizable(URL_1));
    }

    @Test
    public void testNotMarfeelizableSiteUrl() throws IOException {
        when(document.title()).thenReturn(NOT_MARFEELIZABLE_TITLE);
        assertFalse(toTest.marfeelizable(URL_1));
    }

}