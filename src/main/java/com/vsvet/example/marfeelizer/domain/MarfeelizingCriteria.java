package com.vsvet.example.marfeelizer.domain;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Entity
@Table(name = "marfeelizing_criterias")
public class MarfeelizingCriteria extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private MarfeelizingCriteriaType type;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "criteria_properties", joinColumns = @JoinColumn(name = "id"))
    @MapKeyColumn(name = "key")
    @Column(name = "value")
    private Map<String, String> properties = new HashMap<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MarfeelizingCriteriaType getType() {
        return type;
    }

    public void setType(MarfeelizingCriteriaType type) {
        this.type = type;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MarfeelizingCriteria)) return false;
        MarfeelizingCriteria that = (MarfeelizingCriteria) o;
        return Objects.equals(getName(), that.getName()) &&
                getType() == that.getType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getType());
    }
}
