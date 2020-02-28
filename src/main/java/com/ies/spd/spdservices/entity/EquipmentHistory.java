package com.ies.spd.spdservices.entity;

import java.util.Date;
import java.util.List;

/**
 * @Athor: 吴广庆
 * @Date: 2019-08-02
 */
public class EquipmentHistory {
    private Date nowdate;
    private List<EquiHistory> equiHistoryList;

    public Date getNowdate() {
        return nowdate;
    }

    public void setNowdate(Date nowdate) {
        this.nowdate = nowdate;
    }

    public List<EquiHistory> getEquiHistoryList() {
        return equiHistoryList;
    }

    public void setEquiHistoryList(List<EquiHistory> equiHistoryList) {
        this.equiHistoryList = equiHistoryList;
    }
}