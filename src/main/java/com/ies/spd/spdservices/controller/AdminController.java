package com.ies.spd.spdservices.controller;

import com.alibaba.fastjson.JSON;
import com.ies.spd.spdservices.controller.entity.MapValue;
import com.ies.spd.spdservices.controller.entity.MonthYear;
import com.ies.spd.spdservices.entity.Area;
import com.ies.spd.spdservices.entity.EquipmentHistory;
import com.ies.spd.spdservices.entity.Version;
import com.ies.spd.spdservices.service.AreaService;
import com.ies.spd.spdservices.service.EquipmentEventLogService;
import com.ies.spd.spdservices.service.EquipmentService;
import com.ies.spd.spdservices.service.VersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * @Athor: 吴广庆
 * @Date: 2019-06-17
 */
@RestController
@RequestMapping(value = "/home")
public class AdminController {

    @Autowired
    private AreaService areaService;

    @Autowired
    private VersionService versionService;

    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private EquipmentEventLogService equipmentEventLogService;

    /**
     * 废弃
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/areachildren")
    public List<Area> getAreaChildren(){
        List<Area> areaList = areaService.getAreaChildren();
        System.out.println();
        System.out.println(JSON.toJSONString(areaList));
        return areaList;
    }

    /**
     * 获取地图所需要显示数据（获取有故障、预警的片区名称、坐标、及受损设备数量）
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getMapValue")
    public MapValue getMapValue()throws Exception{
        return areaService.getMapValue();
    }

   /**
     * 按片区统计（左中）
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/areaCount")
    public List<Area> areaCount()throws Exception{
        return areaService.getRootCount();
    }

   /**
     * 按型号统计（左上角）
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/versionCount")
    public List<Version> versionCount()throws Exception{
        return versionService.getRootCount();
    }

    /**
     * 故障统计（右上角）
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/faultCount")
    public Map<String, Integer>  faultCount()throws Exception{
        return equipmentService.getEquipmentAllCount();
    }

    /**
     * 右下日历；统计一个月内获得的预警信息
     * @param value
     * @return
     */
    @RequestMapping(value = "/warnDateSelect")
    public List<EquipmentHistory> warnDateSelect(@RequestBody Map<String,Integer> value)throws Exception{
        int thisMonth;
        int thisYear;
        MonthYear monthYear = new MonthYear(value).invoke();
        thisMonth = monthYear.getThisMonth();
        thisYear = monthYear.getThisYear();
        List<EquipmentHistory> equipmentHistoryList = equipmentEventLogService.warnDateSelect(thisMonth,thisYear);
        return equipmentHistoryList;
    }

    /**
     * 右下日历；统计一个月内获得的故障信息
     * @param value
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/dangerDateSelect")
    public List<EquipmentHistory> dangerDateSelect(@RequestBody Map<String,Integer> value)throws Exception{
        int thisMonth;
        int thisYear;
        MonthYear monthYear = new MonthYear(value).invoke();
        thisMonth = monthYear.getThisMonth();
        thisYear = monthYear.getThisYear();
        List<EquipmentHistory> equipmentHistoryList = equipmentEventLogService.dangerDateSelect(thisMonth,thisYear);
        return equipmentHistoryList;
    }
}
