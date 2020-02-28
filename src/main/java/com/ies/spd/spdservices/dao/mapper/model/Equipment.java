package com.ies.spd.spdservices.dao.mapper.model;

import java.util.Date;

public class Equipment {
    private Integer id;

    private String spdNo;

    private Integer areaId;

    private Integer areaPId;

    private Integer versionId;

    private Date updateTime;

    private Boolean isDelete;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSpdNo() {
        return spdNo;
    }

    public void setSpdNo(String spdNo) {
        this.spdNo = spdNo == null ? null : spdNo.trim();
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getAreaPId() {
        return areaPId;
    }

    public void setAreaPId(Integer areaPId) {
        this.areaPId = areaPId;
    }

    public Integer getVersionId() {
        return versionId;
    }

    public void setVersionId(Integer versionId) {
        this.versionId = versionId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }
}