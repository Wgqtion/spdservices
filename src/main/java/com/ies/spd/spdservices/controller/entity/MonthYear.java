package com.ies.spd.spdservices.controller.entity;

import com.ies.spd.spdservices.controller.AdminController;

import java.util.Calendar;
import java.util.Map;

/**
 * @Athor: 吴广庆
 * @Date: 2019-08-07
 */
public class MonthYear {
    private Map<String, Integer> value;
    private int thisMonth;
    private int thisYear;

    public MonthYear(Map<String, Integer> value) {
        this.value = value;
    }

    public int getThisMonth() {
        return thisMonth;
    }

    public int getThisYear() {
        return thisYear;
    }

    public MonthYear invoke() {
        Calendar cal = Calendar.getInstance();
        thisMonth  = cal.get(Calendar.MONTH) + 1;
        thisYear = cal.get(Calendar.YEAR);
        if(value != null){
            if(value.get("thisMonth") != null && value.get("thisMonth") != 0){
                thisMonth  = value.get("thisMonth");
            }if(value.get("thisYear") != null || value.get("thisYear") != 0){
                thisYear = value.get("thisYear");
            }
        }
        return this;
    }
}
