package com.vsvet.example.marfeelizer.view;

import com.vsvet.example.marfeelizer.validator.NotNullOrBlank;
import com.vsvet.example.marfeelizer.validator.SiteDoesNotExist;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.URL;

import java.time.Instant;
import java.util.List;

public class SiteView {

    @NotNullOrBlank(fieldName = "url")
    @SiteDoesNotExist
    @URL
    private String url;

    @ApiModelProperty(readOnly = true)
    private MarfeelizingStatusView status;

    @ApiModelProperty(readOnly = true)
    private Instant createdDate;

    @ApiModelProperty(readOnly = true)
    private Instant modifiedDate;

    @ApiModelProperty(readOnly = true)
    private List<MarfeelizingCriteriaView> criteriaViews;

    public List<MarfeelizingCriteriaView> getCriteriaViews() {
        return criteriaViews;
    }

    public void setCriteriaViews(List<MarfeelizingCriteriaView> criteriaViews) {
        this.criteriaViews = criteriaViews;
    }

    public MarfeelizingStatusView getStatus() {
        return status;
    }

    public void setStatus(MarfeelizingStatusView status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

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

    @Override
    public String toString() {
        return "SiteView{" +
                "url='" + url + '\'' +
                ", status=" + status +
                '}';
    }
}
