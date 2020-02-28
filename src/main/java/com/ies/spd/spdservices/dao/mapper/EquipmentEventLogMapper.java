package com.ies.spd.spdservices.dao.mapper;

import com.ies.spd.spdservices.entity.EquipmentEventLog;
import com.ies.spd.spdservices.entity.EquipmentHistory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface EquipmentEventLogMapper {
    int insert(EquipmentEventLog record);
    List<EquipmentHistory> warnDateSelect(@Param("thisMonth") int thisMonth,@Param("thisYear") int thisYear);
    List<EquipmentHistory> dangerDateSelect(@Param("thisMonth") int thisMonth,@Param("thisYear") int thisYear);
    int insertSelective(EquipmentEventLog record);
}