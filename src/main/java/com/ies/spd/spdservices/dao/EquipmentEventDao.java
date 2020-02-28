package com.ies.spd.spdservices.dao;

import com.ies.spd.spdservices.entity.Equipment;
import com.ies.spd.spdservices.entity.EquipmentEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Athor: 吴广庆
 * @Date: 2019-07-15
 */
public interface EquipmentEventDao extends JpaSpecificationExecutor<EquipmentEvent>,JpaRepository<EquipmentEvent,Long> {

    EquipmentEvent getEquipmentEventByEquipment(Equipment equipment);

    EquipmentEvent getEquipmentEventBySpdNo(String spdNo);

//    Page<EquipmentEvent> findAll(Specification<EquipmentEvent> equipmentEventSpecification, Pageable pageable);
}
