package com.ies.spd.spdservices.entity;

import com.ies.spd.spdservices.constants.Constants;

import javax.persistence.*;
import java.util.Date;

/**
 * @Athor: 吴广庆
 * @Date: 2019-07-10
 */
@Entity
@Table(name = Constants.TABLE_PREFIX +"version")
public class Version {
    private Long id;
    private String versionNo;
    private Integer lifeThreshold;
    private String description;
    private Integer eqCount;
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

    /**
     * @return 设备编号
     */
    public String getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(String versionNo) {
        this.versionNo = versionNo;
    }

    /**
     * @return 寿命报警阈值
     */
    public Integer getLifeThreshold() {
        return lifeThreshold;
    }

    public void setLifeThreshold(Integer lifeThreshold) {
        this.lifeThreshold = lifeThreshold;
    }

    /**
     * @return 设备描述
     */
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "IS_DELETE",updatable = false)
    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    @Transient
    public Integer getEqCount() {
        return eqCount;
    }

    public void setEqCount(Integer eqCount) {
        this.eqCount = eqCount;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
