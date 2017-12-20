package com.vsvet.example.marfeelizer.service;

import com.vsvet.example.marfeelizer.view.MarfeelizingStatusView;
import com.vsvet.example.marfeelizer.view.SiteView;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Validated
public interface SiteService {

    List<SiteView> create(@Valid List<@Valid SiteView> sites);

    List<SiteView> findAll();

    List<SiteView> findAllByStatus(MarfeelizingStatusView statusView);

}
