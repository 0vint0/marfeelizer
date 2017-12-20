package com.vsvet.example.marfeelizer.service.impl;

import com.google.common.collect.Maps;
import com.vsvet.example.marfeelizer.domain.MarfeelizingCriteria;
import com.vsvet.example.marfeelizer.domain.MarfeelizingStatus;
import com.vsvet.example.marfeelizer.domain.Site;
import com.vsvet.example.marfeelizer.repository.MarfeelizingCriteriaRepository;
import com.vsvet.example.marfeelizer.repository.SiteRepository;
import com.vsvet.example.marfeelizer.service.MarfeelizeService;
import com.vsvet.example.marfeelizer.service.SiteService;
import com.vsvet.example.marfeelizer.view.MarfeelizingStatusView;
import com.vsvet.example.marfeelizer.view.SiteView;
import com.vsvet.example.marfeelizer.view.converter.SiteConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
@Transactional
public class SiteServiceImpl implements SiteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SiteServiceImpl.class.getName());

    private final MarfeelizeService marfeelizeService;
    private final SiteRepository siteRepository;
    private final MarfeelizingCriteriaRepository marfeelizingCriteriaRepository;

    public SiteServiceImpl(MarfeelizeService marfeelizeService, SiteRepository siteRepository, MarfeelizingCriteriaRepository marfeelizingCriteriaRepository) {
        this.marfeelizeService = Objects.requireNonNull(marfeelizeService);
        this.siteRepository = Objects.requireNonNull(siteRepository);
        this.marfeelizingCriteriaRepository = Objects.requireNonNull(marfeelizingCriteriaRepository);
    }

    @Override
    public List<SiteView> findAll() {
        SiteConverter siteConverter = new SiteConverter();
        return siteRepository.findAll()
                .stream()
                .map(siteConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<SiteView> findAllByStatus(MarfeelizingStatusView statusView) {
        SiteConverter siteConverter = new SiteConverter();
        return siteRepository.findAllByStatus(MarfeelizingStatus.valueOf(statusView.name()))
                .stream()
                .map(siteConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<SiteView> create(List<@Valid SiteView> sites) {
        Map<String, List<Checker>> entries = getMarfeelizerCheckers(sites);
        waitChecking(entries);
        return createMarfeelizedSites(entries);
    }


    private void waitChecking(Map<String, List<Checker>> entries) {
        CompletableFuture.allOf(
                entries.values()
                        .stream()
                        .flatMap(l -> l.stream())
                        .map(c -> c.criteriaCheckAction)
                        .toArray(CompletableFuture[]::new)).join();
    }

    private List<SiteView> createMarfeelizedSites(Map<String, List<Checker>> entries) {
        SiteConverter siteConverter = new SiteConverter();
        List<Site> sites = entries.entrySet()
                .stream()
                .map(this::create)
                .collect(Collectors.toList());
        return siteRepository.saveAll(sites)
                .stream()
                .map(siteConverter::convert)
                .collect(Collectors.toList());

    }

    private Map<String, List<Checker>> getMarfeelizerCheckers(List<@Valid SiteView> sites) {
        List<MarfeelizingCriteria> criterias = marfeelizingCriteriaRepository.findAll();
        return sites.stream()
                .map(s -> s.getUrl())
                .map(url -> Maps.immutableEntry(url, getCheckers(url, criterias)))
                .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
    }

    private List<Checker> getCheckers(String url, List<MarfeelizingCriteria> criterias) {
        return criterias
                .stream()
                .map(c -> new Checker(c, marfeelizeService.checkCriteria(url, c)))
                .collect(Collectors.toList());
    }

    private Site create(Map.Entry<String, List<Checker>> entry) {
        Site site;
        LOGGER.info("{} Creating site for url {}", Thread.currentThread().getName(), entry.getKey());
        try {
            site = createWithSuccess(entry.getKey(), get(entry.getValue()));
        } catch (Exception e) {
            LOGGER.warn("Error checking url {}", entry.getKey(), e);
            site = createWithFailure(entry.getKey());
        }
        return site;
    }

    private List<MarfeelizingCriteria> get(List<Checker> checkers) {
        return checkers.stream()
                .filter(this::isMarfeelizable)
                .map(c -> c.criteria)
                .collect(Collectors.toList());
    }

    private boolean isMarfeelizable(Checker checker) {
        try {
            return checker.criteriaCheckAction.get();
        } catch (InterruptedException | ExecutionException e) {
            LOGGER.warn("", e);
            return false;
        }
    }

    private Site createWithSuccess(String url, List<MarfeelizingCriteria> criterias) {
        Site site = new Site();
        site.setUrl(url);
        if (!criterias.isEmpty()) {
            site.getCriterias().addAll(criterias);
            site.setStatus(MarfeelizingStatus.MARFEELIZABLE);
        } else {
            site.setStatus(MarfeelizingStatus.UNMARFEELIZABLE);
        }
        return site;
    }

    private Site createWithFailure(String url) {
        Site site = new Site();
        site.setUrl(url);
        site.setStatus(MarfeelizingStatus.FAILED);
        return site;
    }

    class Checker {

        MarfeelizingCriteria criteria;

        CompletableFuture<Boolean> criteriaCheckAction;

        public Checker(MarfeelizingCriteria criteria, CompletableFuture<Boolean> criteriaCheckAction) {
            this.criteria = criteria;
            this.criteriaCheckAction = criteriaCheckAction;
        }
    }
}
