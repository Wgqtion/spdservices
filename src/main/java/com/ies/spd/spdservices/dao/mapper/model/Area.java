package com.ies.spd.spdservices.dao.mapper.model;

import java.util.Date;

public class Area {
    private Integer id;

    private String name;

    private String pCode;

    private String itudeLong;

    private String itudeLat;

    private Date updateDate;

    private String code;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getpCode() {
        return pCode;
    }

    public void setpCode(String pCode) {
        this.pCode = pCode == null ? null : pCode.trim();
    }

    public String getItudeLong() {
        return itudeLong;
    }

    public void setItudeLong(String itudeLong) {
        this.itudeLong = itudeLong == null ? null : itudeLong.trim();
    }

    public String getItudeLat() {
        return itudeLat;
    }

    public void setItudeLat(String itudeLat) {
        this.itudeLat = itudeLat == null ? null : itudeLat.trim();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }
}