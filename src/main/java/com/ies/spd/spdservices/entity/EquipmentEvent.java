package com.ies.spd.spdservices.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ies.spd.spdservices.constants.Constants;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Athor: 吴广庆
 * @Date: 2019-07-10
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = Constants.TABLE_PREFIX + "equipment_event")
public class EquipmentEvent {

    private Long id;
    private Gateway gateway;
    private Equipment equipment;
    private String spdNo;
    private Integer status;
    private Integer silc;
    private Integer silcL1;
    private Integer silcL2;
    private Integer silcL3;
    private Integer silcL4;
    private Integer lightningAmplitude;
    private Integer lightningAmplitudeL1;
    private Integer lightningAmplitudeL2;
    private Integer lightningAmplitudeL3;
    private Integer lightningAmplitudeL4;
    private Integer lightningCount;
    private Integer lightningCountL1;
    private Integer lightningCountL2;
    private Integer lightningCountL3;
    private Integer lightningCountL4;
    private Integer lifeTerm;
    private Integer lifeTermL1;
    private Integer lifeTermL2;
    private Integer lifeTermL3;
    private Integer lifeTermL4;
    private Integer edition;
    private Date updateTime;
    private Date createTime;
    private Date lightningTime;

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


    @OneToOne
    @JoinColumn(name = "SPD_ID",updatable = false)
    @JsonBackReference(value = "equipment")
    public Equipment getEquipment() {
        return equipment;
    }

    @OneToOne
    @NotFound(action= NotFoundAction.IGNORE)
    @JoinColumn(name = "GATEWAY_ID")
    public Gateway getGateway() {
        return gateway;
    }

    public void setGateway(Gateway gateway) {
        this.gateway = gateway;
    }

    @JsonIgnore
    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public String getSpdNo() {
        return spdNo;
    }

    public void setSpdNo(String spdNo) {
        this.spdNo = spdNo;
    }

    /**
     * 设备上报类型0x11心跳0x22事件
     *
     * @return
     */
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Transient
    public Integer getSilc() {
        if (silcL1 != null && silcL2 != null && silcL3 != null && silcL4 != null) {
            silc = silcL1 > silcL2 ? (silcL1 > silcL3 ? (silcL1 > silcL4 ? silcL1 : silcL4) : (silcL3 > silcL4 ? silcL3 : silcL4)) : (silcL2 > silcL3 ? (silcL2 > silcL4 ? silcL2 : silcL4) : (silcL3 > silcL4 ? silcL3 : silcL4));
        }
        return silc;
    }

    /**
     * @return 漏电流
     */
    @Column(name = "SILC_L1")
    public Integer getSilcL1() {
        return silcL1;
    }

    public void setSilcL1(Integer silcL1) {
        this.silcL1 = silcL1;
    }

    @Column(name = "SILC_L2")
    public Integer getSilcL2() {
        return silcL2;
    }

    public void setSilcL2(Integer silcL2) {
        this.silcL2 = silcL2;
    }

    @Column(name = "SILC_L3")
    public Integer getSilcL3() {
        return silcL3;
    }

    public void setSilcL3(Integer silcL3) {
        this.silcL3 = silcL3;
    }

    @Column(name = "SILC_L4")
    public Integer getSilcL4() {
        return silcL4;
    }

    public void setSilcL4(Integer silcL4) {
        this.silcL4 = silcL4;
    }

    @Transient
    public Integer getLightningAmplitude() {
        if (lightningAmplitudeL1 != null && lightningAmplitudeL2 != null && lightningAmplitudeL3 != null && lightningAmplitudeL4 != null) {
            lightningAmplitude = lightningAmplitudeL1 > lightningAmplitudeL2 ? (lightningAmplitudeL1 > lightningAmplitudeL3 ? (lightningAmplitudeL1 > lightningAmplitudeL4 ? lightningAmplitudeL1 : lightningAmplitudeL4) : (lightningAmplitudeL3 > lightningAmplitudeL4 ? lightningAmplitudeL3 : lightningAmplitudeL4)) : (lightningAmplitudeL2 > lightningAmplitudeL3 ? (lightningAmplitudeL2 > lightningAmplitudeL4 ? lightningAmplitudeL2 : lightningAmplitudeL4) : (lightningAmplitudeL3 > lightningAmplitudeL4 ? lightningAmplitudeL3 : lightningAmplitudeL4));
        }
        return lightningAmplitude;
    }

    @Column(name = "LIGHTNING_AMPLITUDE_L1")
    public Integer getLightningAmplitudeL1() {
        return lightningAmplitudeL1;
    }

    public void setLightningAmplitudeL1(Integer lightningAmplitudeL1) {
        this.lightningAmplitudeL1 = lightningAmplitudeL1;
    }

    @Column(name = "LIGHTNING_AMPLITUDE_L2")
    public Integer getLightningAmplitudeL2() {
        return lightningAmplitudeL2;
    }

    public void setLightningAmplitudeL2(Integer lightningAmplitudeL2) {
        this.lightningAmplitudeL2 = lightningAmplitudeL2;
    }

    @Column(name = "LIGHTNING_AMPLITUDE_L3")
    public Integer getLightningAmplitudeL3() {
        return lightningAmplitudeL3;
    }

    public void setLightningAmplitudeL3(Integer lightningAmplitudeL3) {
        this.lightningAmplitudeL3 = lightningAmplitudeL3;
    }

    @Column(name = "LIGHTNING_AMPLITUDE_L4")
    public Integer getLightningAmplitudeL4() {
        return lightningAmplitudeL4;
    }

    public void setLightningAmplitudeL4(Integer lightningAmplitudeL4) {
        this.lightningAmplitudeL4 = lightningAmplitudeL4;
    }

    @Transient
    public Integer getLightningCount() {
        if(lightningCountL1!=null&&lightningCountL2!=null&&lightningCountL3!=null&&lightningCountL4!=null){
            lightningCount = lightningCountL1 + lightningCountL2 + lightningCountL3 + lightningCountL4;
        }
        return lightningCount;
    }

    @Column(name = "LIGHTNING_COUNT_L1")
    public Integer getLightningCountL1() {
        return lightningCountL1;
    }

    public void setLightningCountL1(Integer lightningCountL1) {
        this.lightningCountL1 = lightningCountL1;
    }

    @Column(name = "LIGHTNING_COUNT_L2")
    public Integer getLightningCountL2() {
        return lightningCountL2;
    }

    public void setLightningCountL2(Integer lightningCountL2) {
        this.lightningCountL2 = lightningCountL2;
    }

    @Column(name = "LIGHTNING_COUNT_L3")
    public Integer getLightningCountL3() {
        return lightningCountL3;
    }

    public void setLightningCountL3(Integer lightningCountL3) {
        this.lightningCountL3 = lightningCountL3;
    }

    @Column(name = "LIGHTNING_COUNT_L4")
    public Integer getLightningCountL4() {
        return lightningCountL4;
    }

    public void setLightningCountL4(Integer lightningCountL4) {
        this.lightningCountL4 = lightningCountL4;
    }

    @Transient
    public Integer getLifeTerm() {
        if (lifeTermL1 != null && lifeTermL2 != null && lifeTermL3 != null && lifeTermL4 != null) {
            lifeTerm = lifeTermL1 < lifeTermL2 ? (lifeTermL1 < lifeTermL3 ? (lifeTermL1 < lifeTermL4 ? lifeTermL1 : lifeTermL4) : (lifeTermL3 < lifeTermL4 ? lifeTermL3 : lifeTermL4)) : (lifeTermL2 < lifeTermL3 ? (lifeTermL2 < lifeTermL4 ? lifeTermL2 : lifeTermL4) : (lifeTermL3 < lifeTermL4 ? lifeTermL3 : lifeTermL4));
        }
        return lifeTerm;
    }

    @Column(name = "LIFE_TERM_L1")
    public Integer getLifeTermL1() {
        return lifeTermL1;
    }

    public void setLifeTermL1(Integer lifeTermL1) {
        this.lifeTermL1 = lifeTermL1;
    }

    @Column(name = "LIFE_TERM_L2")
    public Integer getLifeTermL2() {
        return lifeTermL2;
    }

    public void setLifeTermL2(Integer lifeTermL2) {
        this.lifeTermL2 = lifeTermL2;
    }

    @Column(name = "LIFE_TERM_L3")
    public Integer getLifeTermL3() {
        return lifeTermL3;
    }

    public void setLifeTermL3(Integer lifeTermL3) {
        this.lifeTermL3 = lifeTermL3;
    }

    @Column(name = "LIFE_TERM_L4")
    public Integer getLifeTermL4() {
        return lifeTermL4;
    }

    public void setLifeTermL4(Integer lifeTermL4) {
        this.lifeTermL4 = lifeTermL4;
    }

    /**
     * 版本
     *
     * @return
     */
    public Integer getEdition() {
        return edition;
    }

    public void setEdition(Integer edition) {
        this.edition = edition;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getLightningTime() {
        return lightningTime;
    }

    public void setLightningTime(Date lightningTime) {
        this.lightningTime = lightningTime;
    }

    @Column(name = "CREATE_TIME",updatable = false,insertable = true)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
