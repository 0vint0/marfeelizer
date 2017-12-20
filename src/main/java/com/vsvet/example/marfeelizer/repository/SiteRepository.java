package com.vsvet.example.marfeelizer.repository;

import com.vsvet.example.marfeelizer.domain.MarfeelizingStatus;
import com.vsvet.example.marfeelizer.domain.Site;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SiteRepository extends JpaRepository<Site, Long> {

    List<Site> findAllByStatus(MarfeelizingStatus status);

    Optional<Site> findByUrl(String url);
}
