package com.ies.spd.spdservices.service;

import com.alibaba.fastjson.JSON;
import com.ies.spd.spdservices.controller.entity.MapValue;
import com.ies.spd.spdservices.dao.AreaDao;
import com.ies.spd.spdservices.dao.VAreaDao;
import com.ies.spd.spdservices.dao.mapper.AreaMapper;
import com.ies.spd.spdservices.entity.Area;
import com.ies.spd.spdservices.entity.Equipment;
import com.ies.spd.spdservices.entity.EquipmentEvent;
import com.ies.spd.spdservices.entity.VArea;
import com.ies.spd.spdservices.utils.Log4jUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.*;

/**
 * @Athor: 吴广庆
 * @Date: 2019-07-24
 */
@Service
public class AreaService {
    @Autowired
    private AreaDao areaDao;

    @Autowired
    private VAreaDao vAreaDao;

    @Autowired
    private AreaMapper areaMapper;

    public List<Area> findAll() {
        return areaDao.findAll();
    }

    public List<Area> getAreaChildren() {
        Date nowDate = new Date();
        List<Area> areaList = areaDao.findAll(new Specification<Area>() {

            @Override
            public Predicate toPredicate(Root<Area> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //  查询设备不为空的节点
                criteriaQuery.where(criteriaBuilder.isNotNull(root.join("equipmentList").get("id")));
                criteriaQuery.groupBy(root.get("id"));
                return criteriaQuery.getRestriction();
            }
        });

        return areaList;
    }

    /**
     * @return 根片区
     */
    public List<Area> getRootArea() {
        List<Area> areaList = areaDao.findAllByAreaParentIsNullAndIsDeleteIsFalse();
//        List<VArea> areaList = vAreaDao.findByIsDeleteIsFalse();
        testDel(areaList);
        System.out.println(JSON.toJSONString(areaList));
        return areaList;
    }

    public void testDel(List<Area> areaList) {
        Iterator<Area> iterator = areaList.iterator();
        while (iterator.hasNext()) {
            Area areaCh = iterator.next();
            if (areaCh.getIsDelete() == Boolean.TRUE) iterator.remove();
            if (areaCh !=null && areaCh.getAreaChildren() != null && !areaCh.getAreaChildren().isEmpty()) {
                testDel(areaCh.getAreaChildren());
            }
        }
    }

    //有待简化
    public MapValue getMapValue() {
        Date nowDate = new Date();
        List<Area> areaList = areaDao.findAll(new Specification<Area>() {

            @Override
            public Predicate toPredicate(Root<Area> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //  查询设备不为空的节点
                criteriaQuery.where(criteriaBuilder.isNotNull(root.join("equipmentList", JoinType.LEFT).get("id")));
                criteriaQuery.groupBy(root.get("id"));
                return criteriaQuery.getRestriction();
            }
        });
        //测试数据
        Map<Long, String> good = new HashMap<>();
        List<MapValue> dangerValueList = new ArrayList<>();
        List<MapValue> warnValueList = new ArrayList<>();
        MapValue lassValue = new MapValue();
        for (Area area : areaList) {
            MapValue dangerValue = new MapValue();
            MapValue warnValue = new MapValue();
            List<Object> dangerMsg = new ArrayList<>();
            List<Object> warnMsg = new ArrayList<>();
            //添加经纬度数据
            dangerMsg.add(area.getLongitude());
            dangerMsg.add(area.getLatitude());
            dangerMsg.add(area.getCode());
            warnMsg.add(area.getLongitude());
            warnMsg.add(area.getLatitude());
            warnMsg.add(area.getCode());
            List<Equipment> equipmentList = area.getEquipmentList();
            if (equipmentList != null) {
                int i = 0;
                int j = 0;
                for (Equipment equipment : equipmentList) {

                    EquipmentEvent equipmentEvent = equipment.getEquipmentEvent();

                    if (equipmentEvent != null && !equipment.getIsDelete()) {
                        //  心跳过期 || 剩余寿命为0
                        if (nowDate.getTime() - equipmentEvent.getUpdateTime().getTime() > 1000 * 60 * 60 * 2 || equipmentEvent.getLifeTerm() == 0) {
                            i++;
                        }
                        // 剩余寿命低于阈值的预警数据统计
                        else if (equipmentEvent.getLifeTerm() < equipment.getVersion().getLifeThreshold()) {
                            j++;
                        } else {
                            good.put(area.getId(), area.getName());
                        }
                    }
                }
                // 有设备故障时入力数据
                setMapMsg(dangerValueList, area.getName(), dangerValue, dangerMsg, i);
                setMapMsg(warnValueList, area.getName(), warnValue, warnMsg, j);
            }
        }
        lassValue.setWarnList(warnValueList);
        lassValue.setDangerList(dangerValueList);
        return lassValue;
    }

    /**
     * 有异常信息则添加进返回值，没有则不添加
     *
     * @param warnValueList
     * @param areaName
     * @param warnValue
     * @param warnMsg
     * @param num
     */
    private void setMapMsg(List<MapValue> warnValueList, String areaName, MapValue warnValue, List<Object> warnMsg, int num) {
        if (num != 0) {
            warnMsg.add(num);
            warnValue.setName(areaName);
            warnValue.setValue(warnMsg);
            warnValueList.add(warnValue);
        }
    }

    public List<Area> getRootCount() {
        return areaMapper.getRootCount(true);
    }


    /**
     * 新建或变更片区信息
     *
     * @param area
     * @return
     * @throws Exception
     */
    @Transactional
    public Area save(Area area) throws Exception {
        if (area.getParentCode() != null && !area.getParentCode().isEmpty()) {
            Optional<Area> areaParent = areaDao.findAreaByCode(area.getParentCode());
            if (areaParent == null) {
                throw new RuntimeException("未找到父节点");
            }
            area.setAreaParent(areaParent.get());
        } else {
            area.setAreaParent(null);
        }
        area.setUpdateTime(new Date());
        Area areaUpdate = areaDao.saveAndFlush(area);
        // code 拼接
        String thisCode = String.format("%03d", areaUpdate.getId());
        areaUpdate.setCode(areaUpdate.getAreaParent() == null ? thisCode : areaUpdate.getAreaParent().getCode() + thisCode);
        return areaDao.saveAndFlush(areaUpdate);
    }

    /**
     * 删除片区信息
     *
     * @param area
     * @return
     */
    public int deleteArea(Area area) throws Exception {
        return areaDao.updateIsDelete(area.getCode(), new Date(), Boolean.TRUE);
    }


    /**
     * 恢复删除
     *
     * @param area
     * @return
     */
    public int notDeleteArea(Area area) throws Exception {
        return areaDao.updateIsDelete(area.getCode(), new Date(), Boolean.FALSE);
    }
}
