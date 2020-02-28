package com.ies.spd.spdservices.dao;

import com.ies.spd.spdservices.entity.Gateway;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Athor: 吴广庆
 * @Date: 2019-12-17
 */
@Repository
public interface GatewayDao extends JpaSpecificationExecutor<Gateway>,JpaRepository<Gateway,Long> {

    Gateway findGatewayByHostAddressAndPort(String HostAddress,Integer port);
    List<Gateway> findAllByOnlineIsTrue();
}
