package com.ies.spd.spdservices.dao;

import com.ies.spd.spdservices.entity.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @Athor: 吴广庆
 * @Date: 2019-07-24
 */
@Repository
public interface AreaDao extends JpaSpecificationExecutor<Area>,JpaRepository<Area,Long> {

    List<Area> findAllByAreaParentIsNullAndIsDeleteIsFalse();

    Optional<Area> findAreaByCode(String code);

    @Transactional
    @Modifying
    @Query("UPDATE Area SET isDelete= ?3,updateTime=?2 where code=?1")
    int updateIsDelete(String code, Date updateTime,Boolean isDelete);
}
