package com.ies.spd.spdservices.entity;

import com.ies.spd.spdservices.constants.Constants;
import com.ies.spd.spdservices.utils.HexUtils;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * @Athor: 吴广庆
 * @Date: 2019-11-09
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = Constants.TABLE_PREFIX + "maint_log")
public class MaintLog {

    private Long id;
    private String spdNo;
    private String areaValue= "";
    private Area area;
    private String versionNo;
    private Version version;
    private Integer status;
    private User user;
    private Integer faultType;
    private Integer operType;
    private java.sql.Timestamp maintTime;
    private String description;
    private java.sql.Timestamp faultTime;

    /**
     * @return 设备ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "SPD_NO")
    public String getSpdNo() {
        return spdNo;
    }

    public void setSpdNo(String spdNo) {
        this.spdNo = spdNo;
    }

    @Transient
    public String getAreaValue() {
        if(area != null){
            areaValue = HexUtils.addAreaName(areaValue,area);
        }
        return areaValue;
    }

    @ManyToOne
    @JoinColumn(name = "AREA_ID")
    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    @ManyToOne
    @JoinColumn(name = "VERSION_ID")
    public Version getVersion() {
        return version;
    }

    public void setVersion(Version version) {
        this.version = version;
    }

    public String getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(String versionNo) {
        this.versionNo = versionNo;
    }

    @Column(name = "STATUS")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getFaultType() {
        return faultType;
    }

    public void setFaultType(Integer faultType) {
        this.faultType = faultType;
    }

    public Integer getOperType() {
        return operType;
    }

    public void setOperType(Integer operType) {
        this.operType = operType;
    }

    @Column(name = "MAINT_TIME")
    public java.sql.Timestamp getMaintTime() {
        return maintTime;
    }

    public void setMaintTime(java.sql.Timestamp maintTime) {
        this.maintTime = maintTime;
    }

    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "FAULT_TIME")
    public java.sql.Timestamp getFaultTime() {
        return faultTime;
    }

    public void setFaultTime(java.sql.Timestamp faultTime) {
        this.faultTime = faultTime;
    }

}
