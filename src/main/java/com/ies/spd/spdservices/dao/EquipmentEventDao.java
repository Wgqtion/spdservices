package com.ies.spd.spdservices.dao;

import com.ies.spd.spdservices.entity.Equipment;
import com.ies.spd.spdservices.entity.EquipmentEvent;
import com.ies.spd.spdservices.entity.Gateway;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Athor: 吴广庆
 * @Date: 2019-07-15
 */
@Repository
public interface EquipmentEventDao extends JpaSpecificationExecutor<EquipmentEvent>,JpaRepository<EquipmentEvent,Long> {

    EquipmentEvent getEquipmentEventByEquipment(Equipment equipment);

    EquipmentEvent getEquipmentEventBySpdNo(String spdNo);

    List<EquipmentEvent> getEquipmentEventsByEquipmentIsNull();

    @Query("SELECT spdNo FROM EquipmentEvent")
    List<String> findSpdNo();

    List<EquipmentEvent> getEquipmentEventsByGatewayHostAddress(String hostAddress);

//    @Query("UPDATE EquipmentEvent SET Gateway = ?1 WHERE gateway.hostAddress = ?2")
//    List<String> updateGatway(Gateway gateway,String hostAddress);


//    Page<EquipmentEvent> findAll(Specification<EquipmentEvent> equipmentEventSpecification, Pageable pageable);
}
