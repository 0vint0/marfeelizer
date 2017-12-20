package com.vsvet.example.marfeelizer.service.impl;

import com.vsvet.example.marfeelizer.domain.MarfeelizingCriteria;
import com.vsvet.example.marfeelizer.service.MarfeelizeService;
import com.vsvet.example.marfeelizer.service.MarfeelizerChecker;
import com.vsvet.example.marfeelizer.service.MarfeelizerCheckerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@Service
public class MarfeelizeServiceImpl implements MarfeelizeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MarfeelizeServiceImpl.class);

    private final MarfeelizerCheckerFactory marfeelizerCheckerFactory;

    public MarfeelizeServiceImpl(MarfeelizerCheckerFactory marfeelizerCheckerFactory) {
        this.marfeelizerCheckerFactory = Objects.requireNonNull(marfeelizerCheckerFactory);
    }

    @Override
    @Async
    public CompletableFuture<Boolean> checkCriteria(String url, MarfeelizingCriteria criteria) {
        LOGGER.info("{}:Checking url {} by {} criteria", Thread.currentThread().getName(), url, criteria.getName());
        return CompletableFuture.completedFuture(isMarfeelizable(marfeelizerCheckerFactory.get(criteria), url));
    }

    private boolean isMarfeelizable(MarfeelizerChecker checker, String url) {
        try {
            return checker.marfeelizable(url);
        } catch (IOException e) {
            LOGGER.warn("", e);
            return false;
        }
    }
}

