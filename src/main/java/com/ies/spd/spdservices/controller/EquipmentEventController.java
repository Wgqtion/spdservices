package com.ies.spd.spdservices.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ies.spd.spdservices.constants.PageConstants;
import com.ies.spd.spdservices.entity.Equipment;
import com.ies.spd.spdservices.entity.EquipmentEvent;
import com.ies.spd.spdservices.service.EquipmentEventService;
import com.ies.spd.spdservices.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Athor: 吴广庆
 * @Date: 2019-07-16
 */
@RestController
@RequestMapping(value = "/spd")
public class EquipmentEventController {
    @Autowired
    private EquipmentEventService equipmentEventService;

    @Autowired
    private EquipmentService equipmentService;


    @RequestMapping(value = "/equipmentEventlist")
    public Page<EquipmentEvent> getEquipmentEventList(@RequestBody PageConstants equipmentEventPageConstants)throws Exception {
        System.out.println(JSONObject.toJSONString(equipmentEventPageConstants));
        return equipmentEventService.getEquipmentEventList(equipmentEventPageConstants);
    }

    @RequestMapping(value = "/equipmentList")
    public Page<Equipment> getEquipmentList(@RequestBody PageConstants equipmentEventPageConstants)throws Exception {
        System.out.println(JSONObject.toJSONString(equipmentEventPageConstants));
        Page<Equipment> equipmentPage = equipmentService.getEquipmentList(equipmentEventPageConstants);
        return equipmentPage;
    }

    @RequestMapping(value = "/deleteEquipment")
    public String deleteEquipment(@RequestBody JSONObject data) throws Exception {
        System.out.println(data.getByte("id"));
        int msg = equipmentService.deleteEquipment(Long.valueOf(data.getByte("id")));
        if (msg == 1) {
            return "{code:'200',message:'删除成功'}";
        } else {
            return "{code:'300',message:'删除失败'}";
        }
    }


    @RequestMapping(value = "/save")
    public Equipment save(@RequestBody Equipment equipment) throws Exception {
        System.out.println(JSON.toJSONString(equipment));
        Equipment savemsg = equipmentService.save(equipment);
        return savemsg;
    }

}
