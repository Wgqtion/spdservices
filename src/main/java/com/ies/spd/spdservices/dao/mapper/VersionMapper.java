package com.ies.spd.spdservices.dao.mapper;

import com.ies.spd.spdservices.entity.Version;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface VersionMapper {
    int insert(Version record);
    List<Version> getRootCount();
    int insertSelective(Version record);
}