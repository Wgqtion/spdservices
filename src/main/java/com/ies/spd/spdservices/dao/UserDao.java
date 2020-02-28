package com.ies.spd.spdservices.dao;

import com.ies.spd.spdservices.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

/**
 * @Athor: 吴广庆
 * @Date: 2019-11-18
 */
@Repository
public interface UserDao extends JpaSpecificationExecutor<User>,JpaRepository<User,Long> {

    User findByLoginNameAndPasswordAndIsDelete(String loginName,String password,boolean isDelete);

    @Transactional
    @Modifying
    @Query("UPDATE User SET password= ?2 where loginName=?1")
    int updatePassword(String loginName, String password);

    @Transactional
    @Modifying
    @Query("UPDATE User SET isDelete= true,updateTime=?2 where loginName=?1")
    int updateIsDelete(String loginName, Date updateTime);
}
