package com.ies.spd.spdservices.dao.mapper;

import com.ies.spd.spdservices.entity.Equipment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface EquipmentMapper {
    int insert(Equipment record);

    List<Equipment> findByAll();
//    int insertSelective(Equipment record);
}