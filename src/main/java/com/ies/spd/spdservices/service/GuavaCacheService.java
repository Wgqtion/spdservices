//package com.ies.spd.spdservices.service;
//
//import com.ies.spd.spdservices.entity.Equipment;
//import com.ies.spd.spdservices.entity.EquipmentEvent;
//import com.ies.spd.spdservices.entity.Gateway;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.annotation.CachePut;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class GuavaCacheService {
//
//    @Autowired
//    private EquipmentEventService equipmentEventService;
//
//
//    @Autowired
//    private EquipmentService equipmentService;
//
//    @Autowired
//    private GatewayService gatewayService;
//
//    // 缓存查询
//    @Cacheable(value = "equipmentEventNo")
//    public List<EquipmentEvent> getEquipmentEventNo(){
//        System.out.println("缓存数据");
//        return equipmentEventService.getSpdNoAll();
//    }
//
//    // 缓存刷新
//    @CachePut(value = "equipmentEventNo")
//    public List<EquipmentEvent> putEquipmentEventNo(){
//        System.out.println("缓存刷新");
//        return equipmentEventService.getSpdNoAll();
//    }
//
//    // 缓存查询
//    @Cacheable(value = "equipmentNo")
//    public List<Equipment> getEquipmentNo(){
//        System.out.println("缓存数据");
//        return equipmentService.getSpdNoAll();
//    }
//
//    // 缓存刷新
//    @CachePut(value = "equipmentNo")
//    public List<Equipment> putEquipmentNo(){
//        System.out.println("缓存刷新");
//        return equipmentService.getSpdNoAll();
//    }
//
//    // 缓存查询
//    @Cacheable(value = "gateway")
//    public List<Gateway> getGateway(){
//        System.out.println("缓存数据");
//        return gatewayService.findAll();
//    }
//
//    // 缓存刷新
//    @CachePut(value = "gateway")
//    public List<Gateway> putGateway(){
//        System.out.println("缓存刷新");
//        return gatewayService.findAll();
//    }
//}
//
//
//
