package com.ies.spd.spdservices.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ies.spd.spdservices.constants.Constants;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * @Athor: 吴广庆
 * @Date: 2019-11-09
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = Constants.TABLE_PREFIX +"user")
public class User {
    private Long id;
    private String name;
    private String loginName;
    private String password;
    private String description;
    private Integer level;
    private Date updateTime;
    private Boolean isDelete;


    /**
     * @return 设备ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(unique = true)
    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    @JsonBackReference(value = "password")
    @Column(updatable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "MY_RANK")
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @JsonBackReference(value = "isDelete")
    @Column(name = "IS_DELETE",updatable = false)
    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }
}
