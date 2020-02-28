package com.ies.spd.spdservices.dao;

import com.ies.spd.spdservices.entity.VArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @Athor: 吴广庆
 * @Date: 2020-01-16
 */
public interface VAreaDao extends JpaSpecificationExecutor<VArea>,JpaRepository<VArea,Long> {
    List<VArea> findByIsDeleteIsFalse();
}
