package com.vsvet.example.marfeelizer.service.impl;

import com.vsvet.example.marfeelizer.domain.MarfeelizingCriteria;
import com.vsvet.example.marfeelizer.domain.MarfeelizingCriteriaType;
import com.vsvet.example.marfeelizer.domain.MarfeelizingStatus;
import com.vsvet.example.marfeelizer.domain.Site;
import com.vsvet.example.marfeelizer.repository.MarfeelizingCriteriaRepository;
import com.vsvet.example.marfeelizer.repository.SiteRepository;
import com.vsvet.example.marfeelizer.service.AbstractTest;
import com.vsvet.example.marfeelizer.service.MarfeelizeService;
import com.vsvet.example.marfeelizer.service.SiteService;
import com.vsvet.example.marfeelizer.config.SiteServiceTestConfig;
import com.vsvet.example.marfeelizer.view.MarfeelizingStatusView;
import com.vsvet.example.marfeelizer.view.SiteView;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.vsvet.example.marfeelizer.util.BeanTestUtils.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Tests service business logic
 */
@ContextConfiguration(classes = {SiteServiceTestConfig.class})
public class SiteServiceImplTest extends AbstractTest {

    private SiteView siteView1, siteView2, siteView3;

    private CompletableFuture<Boolean> site1Criteria1, site1Criteria2,
            site2Criteria1, site2Criteria2,
            site3Criteria1, site3Criteria2;
    private Site site1, site2, site3;

    @Mock
    private MarfeelizingCriteria criteria1, criteria2;

    @Autowired
    private SiteRepository siteRepository;
    @Autowired
    private MarfeelizingCriteriaRepository marfeelizingCriteriaRepository;
    @Autowired
    private MarfeelizeService marfeelizeService;
    @Captor
    private ArgumentCaptor<List<Site>> sitesCaptor;

    @Autowired
    private SiteService toTest;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        prepareCompletableFutures();
        prepareSites();
        prepareSiteViews();
        prepareMarfeelizingCriteriaRepository();
        prepareMarfeelizeService();
        prepareSiteRepository();
    }

    @After
    public void cleanup() {
        reset(siteRepository, marfeelizeService, marfeelizingCriteriaRepository);
    }

    //tests for creating sites
    @Test
    public void testCreatingExpectedNumberOfSites() {
        List<SiteView> siteViews = toTest.create(Arrays.asList(siteView1, siteView2, siteView3));
        assertEquals(3, siteViews.size());
    }

    @Test
    public void testDuringCreateSaveExpectedNumberOfSites() {
        toTest.create(Arrays.asList(siteView1, siteView2, siteView3));
        verify(siteRepository).saveAll(sitesCaptor.capture());
        assertEquals(3, sitesCaptor.getValue().size());
    }

    @Test
    public void testSavingSitesWithExpectedUrlValues() {
        toTest.create(Arrays.asList(siteView1, siteView2, siteView3));
        verify(siteRepository).saveAll(sitesCaptor.capture());
        assertEquals(URL_1, sitesCaptor.getValue().get(0).getUrl());
        assertEquals(URL_2, sitesCaptor.getValue().get(1).getUrl());
        assertEquals(URL_3, sitesCaptor.getValue().get(2).getUrl());
    }

    @Test
    public void testSavingSitesWithExpectedStatusValues() {
        toTest.create(Arrays.asList(siteView1, siteView2, siteView3));
        verify(siteRepository).saveAll(sitesCaptor.capture());
        assertEquals(MarfeelizingStatus.MARFEELIZABLE, sitesCaptor.getValue().get(0).getStatus());
        assertEquals(MarfeelizingStatus.MARFEELIZABLE, sitesCaptor.getValue().get(1).getStatus());
        assertEquals(MarfeelizingStatus.UNMARFEELIZABLE, sitesCaptor.getValue().get(2).getStatus());
    }

    @Test
    public void testSavingSitesWithExpectedCriteriaValues() {
        toTest.create(Arrays.asList(siteView1, siteView2, siteView3));
        verify(siteRepository).saveAll(sitesCaptor.capture());
        assertEquals(Arrays.asList(criteria1, criteria2), sitesCaptor.getValue().get(0).getCriterias());
        assertEquals(Arrays.asList(criteria2), sitesCaptor.getValue().get(1).getCriterias());
        assertTrue(sitesCaptor.getValue().get(2).getCriterias().isEmpty());
    }

    @Test
    public void testCreatingSitesGettingExpectedMappedValues() {
        site1.getCriterias().add(criteria());
        List<SiteView> siteViews = toTest.create(Arrays.asList(siteView1, siteView2, siteView3));
        assertEquals(URL_1, siteViews.get(0).getUrl());
        assertEquals(MarfeelizingStatusView.MARFEELIZABLE, siteViews.get(0).getStatus());
        assertEquals(CRITERIA_NAME, siteViews.get(0).getCriteriaViews().get(0).getName());
        assertEquals(MarfeelizingCriteriaType.CONTAINS_TITLE.name(), siteViews.get(0).getCriteriaViews().get(0).getType());
        assertEquals(CRITERIA_PROPERTY_VALUE, siteViews.get(0).getCriteriaViews().get(0).getProperties().get(CRITERIA_PROPERTY_NAME));
    }
    //

    private void prepareSiteViews() {
        siteView1 = siteView1();
        siteView2 = siteView2();
        siteView3 = siteView3();
    }

    private void prepareSites() {
        site1 = site1();
        site2 = site2();
        site3 = site3();
    }

    private void prepareMarfeelizeService() {
        when(marfeelizeService.checkCriteria(URL_1, criteria1)).thenReturn(site1Criteria1);
        when(marfeelizeService.checkCriteria(URL_1, criteria2)).thenReturn(site1Criteria2);
        when(marfeelizeService.checkCriteria(URL_2, criteria1)).thenReturn(site2Criteria1);
        when(marfeelizeService.checkCriteria(URL_2, criteria2)).thenReturn(site2Criteria2);
        when(marfeelizeService.checkCriteria(URL_3, criteria1)).thenReturn(site3Criteria1);
        when(marfeelizeService.checkCriteria(URL_3, criteria2)).thenReturn(site3Criteria2);
    }

    private void prepareCompletableFutures() {
        site1Criteria1 = CompletableFuture.completedFuture(true);
        //to simulate some delay waiting
        site1Criteria2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return true;
        });

        site2Criteria1 = CompletableFuture.completedFuture(false);
        site2Criteria2 = CompletableFuture.completedFuture(true);

        //to simulate some delay waiting
        site3Criteria1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return false;
        });
        site3Criteria2 = CompletableFuture.completedFuture(false);
    }

    private void prepareSiteRepository() {
        when(siteRepository.saveAll(any(Iterable.class))).thenReturn(Arrays.asList(site1, site2, site3));
    }

    private void prepareMarfeelizingCriteriaRepository() {
        when(marfeelizingCriteriaRepository.findAll()).thenReturn(Arrays.asList(criteria1, criteria2));
    }
}
