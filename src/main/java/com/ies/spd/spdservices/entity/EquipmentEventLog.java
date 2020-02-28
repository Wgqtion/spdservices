package com.ies.spd.spdservices.entity;

import com.ies.spd.spdservices.constants.Constants;

import javax.persistence.*;
import java.util.Date;

/**
 * @Athor: 吴广庆
 * @Date: 2019-07-10
 */
@Entity
@Table(name = Constants.TABLE_PREFIX +"equipment_event_log")
public class EquipmentEventLog {

    private Long id;
    private String spdNo;
    private Integer status;
    private String hexMsg;
    private Integer silcL1;
    private Integer silcL2;
    private Integer silcL3;
    private Integer silcL4;
    private Integer lifeTermL1;
    private Integer lifeTermL2;
    private Integer lifeTermL3;
    private Integer lifeTermL4;
    private Integer lightningAmplitudeL1;
    private Integer lightningAmplitudeL2;
    private Integer lightningAmplitudeL3;
    private Integer lightningAmplitudeL4;
    private Integer lightningCountL1;
    private Integer lightningCountL2;
    private Integer lightningCountL3;
    private Integer lightningCountL4;
    private Integer edition;
    private Date updateTime;

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
    public String getSpdNo() {
        return spdNo;
    }

    public void setSpdNo(String spdNo) {
        this.spdNo = spdNo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getHexMsg() {
        return hexMsg;
    }

    public void setHexMsg(String hexMsg) {
        this.hexMsg = hexMsg;
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

    /**
     * @return 剩余寿命
     */
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
     * @return 雷击幅值
     */
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
}
