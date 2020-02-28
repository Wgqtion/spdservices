package com.ies.spd.spdservices.service;

import com.ies.spd.spdservices.constants.PageConstants;
import com.ies.spd.spdservices.dao.MaintLogDao;
import com.ies.spd.spdservices.entity.MaintLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.sql.Timestamp;
import java.util.*;

/**
 * @Athor: 吴广庆
 * @Date: 2019-11-09
 */
@Service
@Transactional
public class MaintLogService {
    @Autowired
    private MaintLogDao maintLogDao;

    public Page<MaintLog> getMaintLogList(PageConstants maintLogPageConstants) {

        Map<String, Integer> eqMap = maintLogPageConstants.getEqMap();
        Map<String, String> likeMap = maintLogPageConstants.getLikeMap();
        PageRequest pageRequest = PageRequest.of(maintLogPageConstants.getPageNum() - 1, maintLogPageConstants.getPageSize());
        // 要查询的状态0正常；1预警；2故障；3非正常
        Long[] timeFrame = maintLogPageConstants.getTimeFrame();
        return maintLogDao.findAll(new Specification<MaintLog>() {
            @Override
            public Predicate toPredicate(Root<MaintLog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                Predicate predicate = criteriaBuilder.conjunction();
                Iterator<Map.Entry<String, Integer>> entries = eqMap.entrySet().iterator();
                List<Predicate> predicateList = new ArrayList<>();
                // 根据设备类型查询设备
                while (entries.hasNext()) {
                    Map.Entry<String, Integer> eqEntry = entries.next();
                    String key = eqEntry.getKey();
                    Integer value = eqEntry.getValue();
                    switch (key) {
                        case "versionId":
                            predicateList.add(criteriaBuilder.equal(root.join("version", JoinType.LEFT).get("id").as(String.class), value));
                    }
                }

                Iterator<Map.Entry<String, String>> likeEntries = likeMap.entrySet().iterator();
                // 根据所在片区查询设备
                while (likeEntries.hasNext()) {
                    Map.Entry<String, String> likeEntry = likeEntries.next();
                    String key = likeEntry.getKey();
                    String value = likeEntry.getValue();
                    switch (key) {
                        // 根据设备编号查询
                        case "spdNo":
                            if(value!=null)
                            predicateList.add(criteriaBuilder.like(root.get("spdNo").as(String.class), "%" + value + "%"));
                            break;
                         // 根据片区id查询
                        case "areaCode":
                            predicateList.add(criteriaBuilder.like(root.join("area", JoinType.LEFT).get("code").as(String.class), "%" + value + "%"));
                            break;
                    }
                }
                // 根据时间范围查询结果
                if (timeFrame != null && timeFrame.length == 2) {
                    predicateList.add(
                            criteriaBuilder.and(
                                    criteriaBuilder.greaterThan(root.get("maintTime").as(java.sql.Timestamp.class), new Timestamp(timeFrame[0])),
                                    criteriaBuilder.lessThanOrEqualTo(root.get("maintTime").as(java.sql.Timestamp.class), new Timestamp(timeFrame[1]))
                            )
                    );
                }


                Predicate[] p = new Predicate[predicateList.size()];
                criteriaQuery.where(criteriaBuilder.and(predicateList.toArray(p)));
                return criteriaQuery.getRestriction();
            }
        }, pageRequest);
    }

    /**
     * 保存
     * @param maintLog
     * @return
     */
    public MaintLog save(MaintLog maintLog){
        return maintLogDao.save(maintLog);
    }
}
