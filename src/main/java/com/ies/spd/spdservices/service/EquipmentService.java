package com.ies.spd.spdservices.service;

import com.alibaba.fastjson.JSON;
import com.ies.spd.spdservices.constants.PageConstants;
import com.ies.spd.spdservices.dao.AreaDao;
import com.ies.spd.spdservices.dao.EquipmentDao;
import com.ies.spd.spdservices.dao.VersionDao;
import com.ies.spd.spdservices.dao.mapper.EquipmentMapper;
import com.ies.spd.spdservices.entity.Area;
import com.ies.spd.spdservices.entity.Equipment;
import com.ies.spd.spdservices.entity.Version;
import com.ies.spd.spdservices.utils.Log4jUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Athor: 吴广庆
 * @Date: 2019-06-28
 */
@Service
@Transactional
public class EquipmentService {

    @Autowired
    private EquipmentDao equipmentDao;
    @Autowired
    private VersionDao versionDao;
    @Autowired
    private AreaDao areaDao;

    @Autowired
    private EquipmentMapper equipmentMapper;

    @Autowired
    private CaffeineService caffeineService;

    public Equipment getEquipmentBySpdNo(String spdNo) {
        Log4jUtils.tcpLog.info("spdNo:" + spdNo);
        List<Equipment> equipmentList = equipmentDao.findAllBySpdNo(spdNo);
        if (equipmentList.size() > 0) {
            return equipmentList.get(0);
        }
        return null;
    }

    public Page<Equipment> getEquipmentList(PageConstants equipmentEventPageConstants) {

        Map<String, Integer> eqMap = equipmentEventPageConstants.getEqMap();
        Map<String, String> likeMap = equipmentEventPageConstants.getLikeMap();
        PageRequest pageRequest = PageRequest.of(equipmentEventPageConstants.getPageNum() - 1, equipmentEventPageConstants.getPageSize());
        // 要查询的状态0正常；1预警；2故障；3非正常
        Integer[] status = equipmentEventPageConstants.getStatus();
        return equipmentDao.findAll(new Specification<Equipment>() {
            @Override
            public Predicate toPredicate(Root<Equipment> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                Predicate predicate = criteriaBuilder.conjunction();
                Iterator<Map.Entry<String, Integer>> entries = eqMap.entrySet().iterator();
                List<Predicate> predicateList = new ArrayList<>();
                List<Predicate> predicateOrList = new ArrayList<>();
                // 根据设备类型查询设备
                while (entries.hasNext()) {
                    Map.Entry<String, Integer> eqEntry = entries.next();
                    String key = eqEntry.getKey();
                    Integer value = eqEntry.getValue();
                    if (key.equals("versionId") && value != null) {
                        predicateList.add(criteriaBuilder.equal(root.join("version", JoinType.LEFT).get("id").as(Long.class), value.longValue()));
                    }
//                    if(key.equals("area.id")){
//                        predicateList.add(criteriaBuilder.like(root.join("area",JoinType.LEFT).get("code").as(String.class), value));
//                    }
                }

                Iterator<Map.Entry<String, String>> likeEntries = likeMap.entrySet().iterator();
                // 根据所在片区查询设备
                while (likeEntries.hasNext()) {
                    Map.Entry<String, String> likeEntry = likeEntries.next();
                    String key = likeEntry.getKey();
                    String value = likeEntry.getValue();
                    if (key.equals("areaCode") && value != null && !value.isEmpty()) {
                        predicateList.add(criteriaBuilder.like(root.join("area", JoinType.LEFT).get("code").as(String.class), "%" + value + "%"));
                    }if (key.equals("spdNo") && value != null && !value.isEmpty()) {
                        predicateList.add(criteriaBuilder.like(root.get("spdNo"), "%" + value + "%"));
                    }
                }
                // 根据设备状态查询设备
                if (status != null) {
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(new Date());
                    cal.add(Calendar.HOUR, -2);
                    for (Integer s:status){
                        switch (s){
                            // 故障
                            case 2:
                                predicateOrList.add(criteriaBuilder.or(
                                        // 判断剩余寿命是否为0
                                        criteriaBuilder.equal(root.join("equipmentEvent", JoinType.LEFT).get("lifeTermL1").as(Integer.class), 0),
                                        criteriaBuilder.equal(root.join("equipmentEvent", JoinType.LEFT).get("lifeTermL2").as(Integer.class), 0),
                                        criteriaBuilder.equal(root.join("equipmentEvent", JoinType.LEFT).get("lifeTermL3").as(Integer.class), 0),
                                        criteriaBuilder.equal(root.join("equipmentEvent", JoinType.LEFT).get("lifeTermL4").as(Integer.class), 0),
                                        // 判断是否掉线
                                        criteriaBuilder.lessThanOrEqualTo(root.join("equipmentEvent", JoinType.LEFT).get("updateTime").as(Date.class), cal.getTime())
                                ));
                                continue;
                                // 预警设备
                            case 1:
                                predicateOrList.add(criteriaBuilder.and(
                                        // 剩余寿命不能为0
                                        criteriaBuilder.notEqual(root.join("equipmentEvent", JoinType.LEFT).get("lifeTermL1").as(Integer.class), 0),
                                        criteriaBuilder.notEqual(root.join("equipmentEvent", JoinType.LEFT).get("lifeTermL2").as(Integer.class), 0),
                                        criteriaBuilder.notEqual(root.join("equipmentEvent", JoinType.LEFT).get("lifeTermL3").as(Integer.class), 0),
                                        criteriaBuilder.notEqual(root.join("equipmentEvent", JoinType.LEFT).get("lifeTermL4").as(Integer.class), 0),
                                        criteriaBuilder.or(
                                                // 有一路剩余寿命小于阈值
                                                criteriaBuilder.lessThanOrEqualTo(root.join("equipmentEvent", JoinType.LEFT).get("lifeTermL1").as(Integer.class), root.join("version", JoinType.LEFT).get("lifeThreshold").as(Integer.class)),
                                                criteriaBuilder.lessThanOrEqualTo(root.join("equipmentEvent", JoinType.LEFT).get("lifeTermL2").as(Integer.class), root.join("version", JoinType.LEFT).get("lifeThreshold").as(Integer.class)),
                                                criteriaBuilder.lessThanOrEqualTo(root.join("equipmentEvent", JoinType.LEFT).get("lifeTermL3").as(Integer.class), root.join("version", JoinType.LEFT).get("lifeThreshold").as(Integer.class)),
                                                criteriaBuilder.lessThanOrEqualTo(root.join("equipmentEvent", JoinType.LEFT).get("lifeTermL4").as(Integer.class), root.join("version", JoinType.LEFT).get("lifeThreshold").as(Integer.class))
                                        ),
                                        // 没有掉线（更新时间在两小时之内）
                                        criteriaBuilder.greaterThan(root.join("equipmentEvent", JoinType.LEFT).get("updateTime").as(Date.class), cal.getTime())
                                ));
                                continue;
                                // 正常
                            case 0:
                                predicateOrList.add(criteriaBuilder.and(
                                        // 剩余寿命都要大于阈值
                                        criteriaBuilder.greaterThan(root.join("equipmentEvent", JoinType.LEFT).get("lifeTermL1").as(Integer.class), root.join("version", JoinType.LEFT).get("lifeThreshold").as(Integer.class)),
                                        criteriaBuilder.greaterThan(root.join("equipmentEvent", JoinType.LEFT).get("lifeTermL2").as(Integer.class), root.join("version", JoinType.LEFT).get("lifeThreshold").as(Integer.class)),
                                        criteriaBuilder.greaterThan(root.join("equipmentEvent", JoinType.LEFT).get("lifeTermL3").as(Integer.class), root.join("version", JoinType.LEFT).get("lifeThreshold").as(Integer.class)),
                                        criteriaBuilder.greaterThan(root.join("equipmentEvent", JoinType.LEFT).get("lifeTermL4").as(Integer.class), root.join("version", JoinType.LEFT).get("lifeThreshold").as(Integer.class)),
                                        // 没有掉线（更新时间在两小时之内）
                                        criteriaBuilder.greaterThan(root.join("equipmentEvent", JoinType.LEFT).get("updateTime").as(Date.class), cal.getTime())
                                        )
                                );
                                continue;
                        }
                    }
                }
                // 未被删除
                predicateList.add(criteriaBuilder.equal(root.get("isDelete").as(Boolean.class),false));
                Predicate[] p = new Predicate[predicateList.size()];
                Predicate  predicateData= criteriaBuilder.and(predicateList.toArray(p));
                if(predicateOrList.size() != 0){
                    Predicate[] p2 = new Predicate[predicateOrList.size()];
                    criteriaQuery.where(criteriaBuilder.and(predicateData,criteriaBuilder.or(predicateOrList.toArray(p2))));
                }else {
                    criteriaQuery.where(predicateData);
                }
                return criteriaQuery.getRestriction();
            }
        }, pageRequest);
    }

    /**
     * @return mybatis查询
     */
    public Map<String, Integer> getEquipmentAllCount() {
        Date nowDate = new Date();
        List<Equipment> equipmentList = equipmentDao.findAllByIsDeleteIsFalse();
        int warnSize = 0;
        int dangerSize = 0;
        int goodSizw = 0;
        Map<String, Integer> stringIntegerMap = new HashMap<>();
        for (Equipment equipment : equipmentList) {
            if(equipment.getEquipmentEvent()!=null){
                if (nowDate.getTime() - equipment.getEquipmentEvent().getUpdateTime().getTime() > 1000 * 60 * 60 * 2 || equipment.getEquipmentEvent().getLifeTerm() == 0) {
                    dangerSize++;
                } else if (equipment.getEquipmentEvent().getLifeTerm() < equipment.getVersion().getLifeThreshold()) {
                    warnSize++;
                } else {
                    goodSizw++;
                }
            }else {
                dangerSize++;
            }
        }
        stringIntegerMap.put("warnSize", warnSize);
        stringIntegerMap.put("dangerSize", dangerSize);
        stringIntegerMap.put("goodSizw", goodSizw);
        return stringIntegerMap;
    }

    /**
     * 删除设备
     */
    public int deleteEquipment(Long id) throws Exception {
        return equipmentDao.updateIsDelete(id,new Date());
    }


    /**
     * 新建或编辑
     */
    public Equipment save(Equipment equipment) throws Exception {
        Optional<Equipment> version = Optional.ofNullable(equipment);
        AtomicReference<Optional<Version>> thisversion = new AtomicReference<>();
        AtomicReference<Optional<Area>> thisarea = new AtomicReference<>();
        version.ifPresent(u-> thisversion.set(versionDao.findById(u.getVersion().getId())));
        version.ifPresent(u-> thisarea.set(areaDao.findAreaByCode(u.getAreaCode())));
//        System.out.println(JSON.toJSONString(thisarea.get()));
        equipment.setVersion(thisversion.get().get());
        equipment.setArea(thisarea.get().get());
        equipment.setUpdateTime(new Date());
        // 操作同时更新缓存
        return caffeineService.putEquipment(equipment);
    }

    public Equipment saveOline(Equipment equipment){
        return equipmentDao.saveAndFlush(equipment);
    }
    public void findVersionById(Optional<Version> thisversion,Long id){
        thisversion = versionDao.findById(id);
    }

    public List<Equipment> getSpdNoAll() {
        return equipmentDao.findAll();
    }
}
