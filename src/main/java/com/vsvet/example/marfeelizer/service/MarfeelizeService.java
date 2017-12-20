package com.vsvet.example.marfeelizer.service;

import com.vsvet.example.marfeelizer.domain.MarfeelizingCriteria;

import java.util.concurrent.CompletableFuture;

public interface MarfeelizeService {

    CompletableFuture<Boolean> checkCriteria(String url, MarfeelizingCriteria criteria);
}
