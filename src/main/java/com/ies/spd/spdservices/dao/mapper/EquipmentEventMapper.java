package com.ies.spd.spdservices.dao.mapper;

import com.ies.spd.spdservices.constants.LightningStatistics;
import com.ies.spd.spdservices.entity.EquipmentEvent;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface EquipmentEventMapper {
    int insert(EquipmentEvent record);

    int insertSelective(EquipmentEvent record);

    List<LightningStatistics> sumLightningCount();
}