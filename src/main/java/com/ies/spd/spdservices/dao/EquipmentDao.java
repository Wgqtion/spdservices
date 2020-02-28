package com.ies.spd.spdservices.dao;

import com.ies.spd.spdservices.entity.Equipment;
import org.apache.ibatis.annotations.Update;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Athor: 吴广庆
 * @Date: 2019-06-28
 */
@Repository
public interface EquipmentDao extends JpaSpecificationExecutor<Equipment>,JpaRepository<Equipment,Long>{

    List<Equipment> findAllBySpdNo(String spdNo);

    List<Equipment> findAllByIsDeleteIsFalse();

    @Transactional
    @Modifying
    @Query("UPDATE Equipment SET isDelete= true,updateTime=?2 where id=?1")
    int updateIsDelete(Long id, Date updateTime);
}
