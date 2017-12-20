package com.vsvet.example.marfeelizer.controller;


import com.vsvet.example.marfeelizer.service.SiteService;
import com.vsvet.example.marfeelizer.view.MarfeelizingStatusView;
import com.vsvet.example.marfeelizer.view.SiteView;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/sites")
public class SiteController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SiteController.class);

    private final SiteService siteService;

    private RestResponses restResponses = RestResponses.json();

    public SiteController(SiteService siteService) {
        this.siteService = Objects.requireNonNull(siteService);
    }


    @ApiOperation(value = "Adds sites to Marfeel.", notes = "Adds sites to Marfeel.")
    @ApiResponses(value = {
            @ApiResponse(code = 412, message = "Validation errors!"),
            @ApiResponse(code = 500, message = "internal server error!"),
            @ApiResponse(code = 201, message = "Success Added!")})
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<List<SiteView>> addSites(@RequestBody List<SiteView> siteViews) {
        LOGGER.info("Called addSites for {} entity", siteViews);
        return restResponses.created(siteService.create(siteViews));
    }

    @ApiOperation(value = "Get all sites from Marfeel.", notes = "Get all sites from Marfeel.")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "internal server error!"),
            @ApiResponse(code = 200, message = "Success!")})
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<List<SiteView>> findAll() {
        LOGGER.info("Called findAll ");
        return restResponses.created(siteService.findAll());
    }

    @ApiOperation(value = "Get all sites by status from Marfeel.", notes = "Get all sites by status from Marfeel.")
    @ApiResponses(value = {
            @ApiResponse(code = 412, message = "Validation errors!"),
            @ApiResponse(code = 500, message = "internal server error!"),
            @ApiResponse(code = 200, message = "Success!")})
    @RequestMapping(value = "/{status}", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<List<SiteView>> findAll(@PathVariable MarfeelizingStatusView status) {
        LOGGER.info("Called findAll by status {}", status);
        return restResponses.created(siteService.findAllByStatus(status));
    }
}
