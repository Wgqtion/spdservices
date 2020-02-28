package com.ies.spd.spdservices.dao.mapper;

import com.ies.spd.spdservices.entity.Gateway;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Athor: 吴广庆
 * @Date: 2019-12-18
 */
@Mapper
@Repository
public interface GatewayMapper {
    List<Gateway> findGateway();
}
