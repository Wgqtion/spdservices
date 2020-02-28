package com.ies.spd.spdservices.entity;


import com.fasterxml.jackson.annotation.*;
import com.ies.spd.spdservices.constants.Constants;
import com.ies.spd.spdservices.utils.CommonUtils;
import com.ies.spd.spdservices.utils.HexUtils;
import javassist.SerialVersionUID;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Athor: 吴广庆
 * @Date: 2019-06-28
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = Constants.TABLE_PREFIX + "equipment")
public class Equipment implements Serializable {

    private Long id;
    private String spdNo;
    // 设备状态
    private Integer status;
    private EquipmentEvent equipmentEvent;
    //    private Area AreaParent;
    private Area area;
    private Version version;
    private String areaName;
    private String areaValue = "";
    private String areaCode;
    private Date updateTime;
    private Boolean isDelete;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "SPD_NO",unique = true)
    public String getSpdNo() {
        return spdNo;
    }

    public void setSpdNo(String spdNo) {
        this.spdNo = spdNo;
    }

    /**
     * 设备状态 0正常1预警2故障
     *
     * @return
     */
    @Transient
    public Integer getStatus() {
        if (equipmentEvent != null && equipmentEvent.getLifeTerm() != null && equipmentEvent.getId() != null) {
            status = CommonUtils.checkStatus(equipmentEvent.getLifeTerm(), Long.valueOf(version.getLifeThreshold()), equipmentEvent.getUpdateTime().getTime());
        } else {
            status = 2;
        }
        return status;
    }

    /**
     * 设备详情，上报动态信息
     *
     * @return
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonManagedReference(value = "equipment")
    @OneToOne(mappedBy = "equipment")
    public EquipmentEvent getEquipmentEvent() {
        return equipmentEvent;
    }

    @JsonIgnore
    public void setEquipmentEvent(EquipmentEvent equipmentEvent) {
        this.equipmentEvent = equipmentEvent;
    }

//    @ManyToOne
//    @JoinColumn(name = "AREA_P_ID")
//    @JsonBackReference
//    public Area getAreaParent() {
//        return AreaParent;
//    }
//
//    public void setAreaParent(Area areaParent) {
//        AreaParent = areaParent;
//    }

    @ManyToOne
    @JoinColumn(name = "AREA_ID")
    @JsonBackReference(value = "area")
    public Area getArea() {
        return area;
    }

    @JsonIgnore
    public void setArea(Area area) {
        this.area = area;
    }

    /**
     * @return 设备型号
     */
    @ManyToOne
    @JoinColumn(name = "VERSION_ID")
    public Version getVersion() {
        return version;
    }

    public void setVersion(Version version) {
        this.version = version;
    }

    @Transient
    public String getAreaValue() {
        if (area != null) {
            areaValue = HexUtils.addAreaName(areaValue, area);
        }
        return areaValue;
    }

    @Transient
    public String getAreaName() {
        if (area != null) {
            areaName = area.getName();
        }
        return areaName;
    }

    @Transient
    public String getAreaCode() {
        if (area != null && area.getId() != null) {
            areaCode = area.getCode();
        }
        return areaCode;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Column(name = "IS_DELETE", updatable = false)
    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }
}
