package com.ies.spd.spdservices.constants;

import org.springframework.data.domain.Sort;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Athor: 吴广庆
 * @Date: 2019-07-16
 */
public class PageConstants {
    Map<String,String> likeMap;
    Map<String,Integer> eqMap;
    Long[] timeFrame;
    Integer[] status;
    Integer pageNum;
    Integer pageSize;
    LinkedHashMap<String,String> sort;
    Sort orders;

    public Sort getOrders() {
        return orders;
    }

    public void setOrders(Sort orders) {
        this.orders = orders;
    }

    public Map<String, String> getLikeMap() {
        return likeMap;
    }

    public void setLikeMap(Map<String, String> likeMap) {
        this.likeMap = likeMap;
    }

    public Map<String, Integer> getEqMap() {
        return eqMap;
    }

    public void setEqMap(Map<String, Integer> eqMap) {
        this.eqMap = eqMap;
    }

    public Long[] getTimeFrame() {
        return timeFrame;
    }

    public void setTimeFrame(Long[] timeFrame) {
        this.timeFrame = timeFrame;
    }

    /**
     * 0正常；1预警；2故障；3非正常
     * @return
     */
    public Integer[] getStatus() {
        return status;
    }

    public void setStatus(Integer[] status) {
        this.status = status;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public LinkedHashMap<String, String> getSort() {
        return sort;
    }

    public void setSort(LinkedHashMap<String, String> sort) {
        this.sort = sort;
    }
}
