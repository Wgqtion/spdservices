package com.ies.spd.spdservices.dao.mapper;

import com.ies.spd.spdservices.entity.EquipmentEvent;

public interface EquipmentEventMapper {
    int insert(EquipmentEvent record);

    int insertSelective(EquipmentEvent record);
}