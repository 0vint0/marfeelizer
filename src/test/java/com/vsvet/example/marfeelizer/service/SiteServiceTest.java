package com.vsvet.example.marfeelizer.service;

import com.vsvet.example.marfeelizer.domain.Site;
import com.vsvet.example.marfeelizer.repository.SiteRepository;
import com.vsvet.example.marfeelizer.config.ErrorMessageMatcher;
import com.vsvet.example.marfeelizer.config.SiteServiceTestConfig;
import com.vsvet.example.marfeelizer.config.ValidationTestConfig;
import com.vsvet.example.marfeelizer.view.SiteView;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

/**
 * On this layer will test only validation
 */
@ContextConfiguration(classes = {ValidationTestConfig.class, SiteServiceTestConfig.class})
public class SiteServiceTest extends AbstractTest {

    private static final String URL_1 = "http://www.google.com";
    private static final String URL_2 = "http://www.shmogel.com";

    private SiteView siteView1, siteView2;

    @Mock
    private Site site;
    @Autowired
    private SiteRepository siteRepository;
    @Autowired
    private SiteService toTest;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        prepareSiteViews();
        prepareSiteRepository();
    }

    //create sites validation tests
    @Test
    public void testCreateSitesWithSuccess() {
        toTest.create(Arrays.asList(siteView1, siteView2));
    }

    @Test
    public void testCreateSitesWithEmptyUrl() {
        thrown.expect(ErrorMessageMatcher.matches("Field 'url' must not be blank."));
        siteView1.setUrl("");
        toTest.create(Arrays.asList(siteView1, siteView2));
    }

    @Test
    public void testCreateSitesWithExistingUrl() {
        thrown.expect(ErrorMessageMatcher.matches("Site '" + URL_1 + "' already was marfeelized checked, please call updated/recheck action."));
        when(siteRepository.findByUrl(URL_1)).thenReturn(Optional.of(site));
        toTest.create(Arrays.asList(siteView1, siteView2));
    }

    @Test
    public void testCreateSitesWithInvalidUrl() {
        thrown.expect(ErrorMessageMatcher.matches("must be a valid URL"));
        siteView1.setUrl("sas/d,.df");
        toTest.create(Arrays.asList(siteView1, siteView2));
    }
    //


    private void prepareSiteViews() {
        siteView1 = new SiteView();
        siteView1.setUrl(URL_1);

        siteView2 = new SiteView();
        siteView2.setUrl(URL_2);
    }

    private void prepareSiteRepository() {
        when(siteRepository.findByUrl(anyString())).thenReturn(Optional.empty());
    }
}
