package com.ies.spd.spdservices.dao.mapper;

import com.ies.spd.spdservices.entity.Area;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AreaMapper {
    int insert(Area record);
    List<Area> getRootCount(@Param("isRoot") Boolean isRoot);
    int insertSelective(Area record);
}