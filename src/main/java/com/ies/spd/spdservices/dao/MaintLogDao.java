package com.ies.spd.spdservices.dao;

import com.ies.spd.spdservices.entity.MaintLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @Athor: 吴广庆
 * @Date: 2019-11-09
 */
@Repository
public interface MaintLogDao extends JpaSpecificationExecutor<MaintLog>,JpaRepository<MaintLog,Long> {
}
