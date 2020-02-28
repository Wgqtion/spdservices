package com.ies.spd.spdservices.controller.entity;

import java.util.List;
import java.util.Map;

/**
 * @Athor: 吴广庆
 * @Date: 2019-07-25
 */
public class MapValue {
    private String name;
    private List<Object> value;
    private List<MapValue> dangerList;
    private List<MapValue> warnList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Object> getValue() {
        return value;
    }

    public void setValue(List<Object> value) {
        this.value = value;
    }

    public List<MapValue> getDangerList() {
        return dangerList;
    }

    public void setDangerList(List<MapValue> dangerList) {
        this.dangerList = dangerList;
    }

    public List<MapValue> getWarnList() {
        return warnList;
    }

    public void setWarnList(List<MapValue> warnList) {
        this.warnList = warnList;
    }
}
