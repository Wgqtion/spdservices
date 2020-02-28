package com.ies.spd.spdservices.entity;

import com.ies.spd.spdservices.constants.Constants;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * 网关实体类
 *
 * @Athor: 吴广庆
 * @Date: 2019-12-17
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = Constants.TABLE_PREFIX + "gateway")
public class Gateway {
    private Long id;
    // 网关IP
    private String hostAddress;
    // 网关端口
    private Integer port;
    // 更新时间
    private Date updateTime;

    private Integer eqCount;
    // 是否在线
    private Boolean online;


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

    public String getHostAddress() {
        return hostAddress;
    }

    public void setHostAddress(String hostAddress) {
        this.hostAddress = hostAddress;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
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
