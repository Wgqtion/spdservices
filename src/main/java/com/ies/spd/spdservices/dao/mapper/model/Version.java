package com.ies.spd.spdservices.dao.mapper.model;

import java.util.Date;

public class Version {
    private Integer id;

    private String versionNo;

    private Integer lifeThreshold;

    private String description;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(String versionNo) {
        this.versionNo = versionNo == null ? null : versionNo.trim();
    }

    public Integer getLifeThreshold() {
        return lifeThreshold;
    }

    public void setLifeThreshold(Integer lifeThreshold) {
        this.lifeThreshold = lifeThreshold;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}