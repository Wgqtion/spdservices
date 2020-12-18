package com.ies.spd.spdservices.dao;

import com.ies.spd.spdservices.entity.EquipmentEventLog;
import com.ies.spd.spdservices.entity.Version;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @Athor: 吴广庆
 * @Date: 2019-07-11
 */
public interface VersionDao  extends JpaSpecificationExecutor<Version>,JpaRepository<Version,Long> {

//    findBy
    @Modifying
    @Query("update Version m set m.isDelete=true where  m.id=?1")
    void removeMyVersion(Long id);
}
