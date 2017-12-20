package com.vsvet.example.marfeelizer.controller;

import com.vsvet.example.marfeelizer.config.ObjectMapperConfig;
import com.vsvet.example.marfeelizer.config.SiteControllerTestConfig;
import com.vsvet.example.marfeelizer.service.AbstractTest;
import com.vsvet.example.marfeelizer.service.SiteService;
import com.vsvet.example.marfeelizer.view.SiteView;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import static com.vsvet.example.marfeelizer.util.BeanTestUtils.URL_1;
import static com.vsvet.example.marfeelizer.util.BeanTestUtils.siteView1;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = {SiteControllerTestConfig.class})
public class SiteControllerTest extends AbstractTest {

    private SiteView siteView;

    @Autowired
    private SiteService siteService;
    @Captor
    private ArgumentCaptor<List<SiteView>> listCaptor;
    @Autowired
    private MockMvc toTest;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        siteView = siteView1();
        prepareSiteServices();
    }

    @After
    public void cleanup() {
        Mockito.reset(siteService);
    }

    @Test
    public void testAddSitesReturnExpectedEntities() throws Exception {
        String content = ObjectMapperConfig.objectMapper().writeValueAsString(Arrays.asList(siteView));
        RequestBuilder requestBuilder = post("/sites")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions resultActions = toTest.perform(requestBuilder);
        resultActions.andExpect(status().isCreated());
        List<LinkedHashMap> siteViews = ObjectMapperConfig.objectMapper().readValue(resultActions.andReturn().getResponse().getContentAsString(), List.class);
        assertEquals(1, siteViews.size());
        assertEquals(URL_1, siteViews.get(0).get("url"));
    }

    @Test
    public void testAddSitesCallSeriviceWithExpectedValue() throws Exception {
        String content = ObjectMapperConfig.objectMapper().writeValueAsString(Arrays.asList(siteView));
        RequestBuilder requestBuilder = post("/sites")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions resultActions = toTest.perform(requestBuilder);
        resultActions.andExpect(status().isCreated());
        verify(siteService).create(listCaptor.capture());
        assertEquals(1, listCaptor.getValue().size());
        assertEquals(URL_1, listCaptor.getValue().get(0).getUrl());
    }

    private void prepareSiteServices() {
        when(siteService.create(any(List.class))).thenReturn(Arrays.asList(siteView));
    }
}