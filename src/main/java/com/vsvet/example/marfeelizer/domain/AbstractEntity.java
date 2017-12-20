package com.vsvet.example.marfeelizer.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.Instant;

@MappedSuperclass
public abstract class AbstractEntity {

    @Column(name = "created_date")
    private Instant createdDate;

    @Column(name = "modified_date")
    private Instant modifiedDate;

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Instant getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Instant modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @PrePersist
    private void onCreate() {
        this.createdDate = Instant.now();
        this.modifiedDate = Instant.now();
    }

    @PreUpdate
    private void onUpdate() {
        this.modifiedDate = Instant.now();
    }
}
