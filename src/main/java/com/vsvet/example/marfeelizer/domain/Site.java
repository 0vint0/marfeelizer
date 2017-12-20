package com.vsvet.example.marfeelizer.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "sites")
public class Site  extends  AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//If using real DB not for exercise here can use sequence (if db offers) to gain batch insert.
    private Long id;

    @Column(name = "url",nullable = false,unique = true)
    private String url;

    @Column(name = "status",nullable = false)
    @Enumerated(EnumType.STRING)
    private MarfeelizingStatus status;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE } )
    @JoinTable(name = "site_criterias",
            joinColumns = @JoinColumn(name = "site_id"),
            inverseJoinColumns = @JoinColumn(name = "marfeelizing_criteria_id")
    )

    private List<MarfeelizingCriteria> criterias = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public MarfeelizingStatus getStatus() {
        return status;
    }

    public void setStatus(MarfeelizingStatus status) {
        this.status = status;
    }

    public List<MarfeelizingCriteria> getCriterias() {
        return criterias;
    }

    public void setCriterias(List<MarfeelizingCriteria> criterias) {
        this.criterias = criterias;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Site)) return false;
        Site site = (Site) o;
        return Objects.equals(getUrl(), site.getUrl());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getUrl());
    }
}
