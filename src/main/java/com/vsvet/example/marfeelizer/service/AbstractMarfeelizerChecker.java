package com.vsvet.example.marfeelizer.service;

import com.vsvet.example.marfeelizer.domain.MarfeelizingCriteria;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.print.Doc;
import java.io.IOException;
import java.util.Objects;

public abstract class AbstractMarfeelizerChecker implements MarfeelizerChecker {

    private final MarfeelizingCriteria criteria;

    protected AbstractMarfeelizerChecker(MarfeelizingCriteria criteria) {
        this.criteria = Objects.requireNonNull(criteria);
    }

    public MarfeelizingCriteria getCriteria() {
        return criteria;
    }

    @Override
    public boolean marfeelizable(String url) throws IOException {
        return checkDocument(getDocument(url));
    }

    protected Document getDocument(String url) throws IOException {
        return Jsoup.connect(url).get();
    }

    protected abstract boolean checkDocument(Document document);
}
