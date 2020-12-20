package com.ies.spd.spdservices.service;

import com.ies.spd.spdservices.entity.Equipment;
import com.ies.spd.spdservices.entity.EquipmentEvent;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CaffeineService {

    @Autowired
    private EquipmentEventService equipmentEventService;


    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private GatewayService gatewayService;


    @Cacheable(value = "equipmentEvent",key = "#spdNo")
    public EquipmentEvent getEquipmentEvent(String spdNo) {
        System.out.println("缓存数据EquipmentEvent");
        return equipmentEventService.getEquipmentEventBySpdNo(spdNo);
    }
    @CachePut(value = "equipmentEvent",key = "#equipmentEvent")
    public EquipmentEvent putEquipmentEvent(EquipmentEvent equipmentEvent) {
        System.out.println("更新缓存数据EquipmentEvent");
        return equipmentEventService.save(equipmentEvent);
    }
    @Cacheable(value = "equipment",key = "#spdNo")
    public Equipment getEquipment(String spdNo) {
        System.out.println("缓存数据Equipment");
        return equipmentService.getEquipmentBySpdNo(spdNo);
    }

    @SneakyThrows
    @CachePut(value = "equipment",key = "#equipment")
    public Equipment putEquipment(Equipment equipment) {
        System.out.println("更新缓存数据Equipment");
        return equipmentService.saveOline(equipment);
    }
}
