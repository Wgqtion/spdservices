package com.ies.spd.spdservices.dao;

import com.ies.spd.spdservices.entity.EquipmentEventLog;
import com.ies.spd.spdservices.entity.Version;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Athor: 吴广庆
 * @Date: 2019-07-11
 */
public interface VersionDao  extends JpaSpecificationExecutor<Version>,JpaRepository<Version,Long> {

//    findBy
}
