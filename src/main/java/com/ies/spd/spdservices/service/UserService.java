package com.ies.spd.spdservices.service;

import com.ies.spd.spdservices.constants.PageConstants;
import com.ies.spd.spdservices.dao.UserDao;
import com.ies.spd.spdservices.entity.Equipment;
import com.ies.spd.spdservices.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.*;

/**
 * @Athor: 吴广庆
 * @Date: 2019-11-18
 */
@Service
@Transactional
public class UserService {

    @Autowired
    private UserDao userDao;

    // TODO （后期变更登陆框架）
    public User login(String loginName, String password) {
        return userDao.findByLoginNameAndPasswordAndIsDelete(loginName, password,false);
    }

    /**
     * 添加及更新
     * @param user
     * @return
     */
    public User save(User user) throws DataIntegrityViolationException {
        return userDao.save(user);
    }

    /**
     * 添加及更新
     * @param loginName
     * @param password
     * @return
     */
    public int updatePassword(String loginName,String password) {
        return userDao.updatePassword(loginName,password);
    }

    /**
     * 更新删除字段用户
     * @param loginName
     * @return
     */
    public int updateIsDelete(String loginName){
        return userDao.updateIsDelete(loginName,new Date());
    }

    /**
     * 查询
     *
     * @param userPageConstants
     * @return
     */
    public Page<User> getUserList(PageConstants userPageConstants) {
        // 全等检索参数
        Map<String, Integer> eqMap = userPageConstants.getEqMap();
        // 模糊检索参数
        Map<String, String> likeMap = userPageConstants.getLikeMap();
        // 分页参数
        PageRequest pageRequest = PageRequest.of(userPageConstants.getPageNum() - 1, userPageConstants.getPageSize());
        return userDao.findAll(new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                // 查询体对象
                Predicate predicate = criteriaBuilder.conjunction();
                Iterator<Map.Entry<String, Integer>> entries = eqMap.entrySet().iterator();
                // 是否入力等级查询参数;（如果有查询等级则显示自己的信息）
                boolean selectLevel = false;
                List<Predicate> predicateList = new ArrayList<>();
                List<Predicate> predicateOrList = new ArrayList<>();
                // 全等检索参数入力
                while (entries.hasNext()) {
                    Map.Entry<String, Integer> eqEntry = entries.next();
                    String key = eqEntry.getKey();
                    Integer value = eqEntry.getValue();
                    // 查询用户等级
                    if (key.equals("eqLevel") && value != null) {
                        // 是否入力等级查询参数
                        selectLevel = true;
                        predicateList.add(criteriaBuilder.equal(root.get("level").as(Integer.class), value));
                    }
                    // 当前用户等级（结果必须大于当前用户等级）
                    else if (key.equals("myLevel") && value != null) {
                        predicateList.add(criteriaBuilder.greaterThan(root.get("level").as(Integer.class), value));
                    }
                    // 当前用户id
                    else if (key.equals("userId") && value != null) {
                        predicateOrList.add(criteriaBuilder.equal(root.get("id").as(Long.class), value.longValue()));
                    }
                }

                Iterator<Map.Entry<String, String>> likeEntries = likeMap.entrySet().iterator();
                // TODO 模糊检索参数入力 登录名全检索
                while (likeEntries.hasNext()) {
                    Map.Entry<String, String> likeEntry = likeEntries.next();
                    String key = likeEntry.getKey();
                    String value = likeEntry.getValue();
                    if (key.equals("likeName") && value != null&& !value.isEmpty()) {
                        predicateList.add(criteriaBuilder.like(root.get("name").as(String.class), "%" + value + "%"));
                    } else if (key.equals("eqLoginName") && value != null&& !value.isEmpty()) {
                        predicateList.add(criteriaBuilder.equal(root.get("loginName").as(String.class), value));
                    }
                }
                Predicate[] p = new Predicate[predicateList.size()];
                Predicate predicateData = criteriaBuilder.and(predicateList.toArray(p));
                if (predicateOrList.size() != 0 && !selectLevel) {
                    Predicate[] p2 = new Predicate[predicateOrList.size()];
                    criteriaQuery.where(criteriaBuilder.or(predicateData, criteriaBuilder.or(predicateOrList.toArray(p2))));
                } else {
                    criteriaQuery.where(predicateData);
                }

                return criteriaQuery.getRestriction();
            }
        }, pageRequest);
    }
}
