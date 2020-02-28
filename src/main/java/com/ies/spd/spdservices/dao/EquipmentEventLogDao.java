package com.ies.spd.spdservices.dao;

import com.ies.spd.spdservices.entity.EquipmentEventLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Athor: 吴广庆
 * @Date: 2019-07-10
 */
public interface EquipmentEventLogDao extends JpaSpecificationExecutor<EquipmentEventLog>,JpaRepository<EquipmentEventLog,Long>  {
}
