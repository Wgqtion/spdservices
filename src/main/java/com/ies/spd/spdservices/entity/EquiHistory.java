package com.ies.spd.spdservices.entity;

import java.util.Date;

/**
 * @Athor: 吴广庆
 * @Date: 2019-08-02
 */
public class EquiHistory {

    private String spdNo;
    private Date upmax;
    private Date upmin;
    private Integer minlife;

    public String getSpdNo() {
        return spdNo;
    }

    public void setSpdNo(String spdNo) {
        this.spdNo = spdNo;
    }

    public Date getUpmax() {
        return upmax;
    }

    public void setUpmax(Date upmax) {
        this.upmax = upmax;
    }

    public Date getUpmin() {
        return upmin;
    }

    public void setUpmin(Date upmin) {
        this.upmin = upmin;
    }

    public Integer getMinlife() {
        return minlife;
    }

    public void setMinlife(Integer minlife) {
        this.minlife = minlife;
    }
}
