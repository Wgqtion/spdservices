package com.ies.spd.spdservices.service;

import com.alibaba.fastjson.JSON;
import com.ies.spd.spdservices.constants.LightningStatistics;
import com.ies.spd.spdservices.constants.PageConstants;
import com.ies.spd.spdservices.dao.EquipmentEventDao;
import com.ies.spd.spdservices.dao.mapper.EquipmentEventLogMapper;
import com.ies.spd.spdservices.dao.mapper.EquipmentEventMapper;
import com.ies.spd.spdservices.entity.Equipment;
import com.ies.spd.spdservices.entity.EquipmentEvent;
import com.ies.spd.spdservices.entity.Gateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @Athor: 吴广庆
 * @Date: 2019-07-15
 */
@Service
@Transactional
public class EquipmentEventService {

    @Autowired
    private EquipmentEventDao equipmentEventDao;

    @Autowired
    private EquipmentEventMapper equipmentEventMapper;

    /**
     * 通过静态设备数据获取当前设备信息
     * @param equipment
     * @return
     */
    public EquipmentEvent getEquipmentEventByEquipment(Equipment equipment){
        return equipmentEventDao.getEquipmentEventByEquipment(equipment);
    }

    /**
     * 通过设备编号查询设备信息
     * @param spdNo
     * @return
     */
    public EquipmentEvent getEquipmentEventBySpdNo(String spdNo){
        return equipmentEventDao.getEquipmentEventBySpdNo(spdNo);
    }

    public Page<EquipmentEvent> getEquipmentEventList(PageConstants equipmentEventPageConstants){

        Map<String,Integer> eqMap= equipmentEventPageConstants.getEqMap();
        Map<String,String> likeMap=equipmentEventPageConstants.getLikeMap();
        PageRequest pageRequest = PageRequest.of(equipmentEventPageConstants.getPageNum() - 1, equipmentEventPageConstants.getPageSize());
        return equipmentEventDao.findAll(new Specification<EquipmentEvent>() {
            @Override
            public Predicate toPredicate(Root<EquipmentEvent> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                Predicate predicate=criteriaBuilder.conjunction();
                Iterator<Map.Entry<String,Integer>> entries = eqMap.entrySet().iterator();
                List<Predicate> predicateList = new ArrayList<>();
                while (entries.hasNext()){

                    Map.Entry<String,Integer> entry = entries.next();
                    String key = entry.getKey();
                    Integer value = entry.getValue();
//                    String[] keys = key.split("\\.");
                    if(key.equals("equipment.version.id")){
                        predicateList.add(criteriaBuilder.equal(root.join("equipment").join("version").get("id").as(Long.class), value.longValue()));
                    }
//                    if(key.equals("equipment.version.id")){
//                        predicateList.add(criteriaBuilder.equal(root.join("equipment").join("version").get("id").as(Long.class), value.longValue()));
//                    }
                }
                predicateList.add(criteriaBuilder.isFalse(root.join("equipment").get("isDelete")));
                Predicate[] p = new Predicate[predicateList.size()];
                criteriaQuery.where(criteriaBuilder.and(predicateList.toArray(p)));
                return criteriaQuery.getRestriction();
            }
        }, pageRequest);
    }

    /**
     * 新建、更新设备动态信息
     * @param equipmentEvent
     */
    public EquipmentEvent save(EquipmentEvent equipmentEvent){
       EquipmentEvent equipmentEventSave = equipmentEventDao.saveAndFlush(equipmentEvent);
       return equipmentEventSave;
    }

    public List<EquipmentEvent> findNewEqipmentByEquipmentEvent(){
       List<EquipmentEvent> equipmentEventList = equipmentEventDao.getEquipmentEventsByEquipmentIsNull();
       return equipmentEventList;
    }

    public List<LightningStatistics> sumLightningCount() {
        List<LightningStatistics> lightningStatisticsList = equipmentEventMapper.sumLightningCount();
        List<LightningStatistics> lightningStatisticsList2 = new ArrayList<>();
        if(lightningStatisticsList != null){
            lightningStatisticsList.get(0).getMymonth();
            LocalDate startDate = LocalDate.parse(lightningStatisticsList.get(0).getMymonth()+"-01");
            LocalDate endDate = LocalDate.now();
            long distance = ChronoUnit.MONTHS.between(startDate, endDate);
            if (distance < 1) {
                return lightningStatisticsList;
            }
            Stream.iterate(startDate, d -> {
                return d.plusMonths(1);
            }).limit(distance + 1).forEach(f -> {
                System.out.println(f.getDayOfYear());
               LightningStatistics lightningStatistics = new LightningStatistics();
                lightningStatistics.setMymonth(f.format(DateTimeFormatter.ofPattern("YYYY-MM")));
                lightningStatistics.setSumlightning(0);
                lightningStatisticsList.stream().forEach(item->{
                    if (item.getMymonth().equals(lightningStatistics.getMymonth())){
                        lightningStatistics.setSumlightning(item.getSumlightning());
                    }
                });
                lightningStatisticsList2.add(lightningStatistics);
            });
            System.out.println(JSON.toJSONString(lightningStatisticsList2));
        }

        return lightningStatisticsList2;
    }

    public List<EquipmentEvent> getSpdNoAll(){
        return equipmentEventDao.findAll();
    }

    /**
     * 查询所有网管IP下的设备
     * @param hostAddress
     * @return
     */
    public List<EquipmentEvent> getEquipmentEventsByGatewayHostAddress(String hostAddress) {
        return equipmentEventDao.getEquipmentEventsByGatewayHostAddress(hostAddress);
    }

//    public void updateGatway(Gateway gateway,String hostAddress){
//        equipmentEventDao.updateGatway(gateway,hostAddress);
//    }
}
