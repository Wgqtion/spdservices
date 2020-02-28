package com.ies.spd.spdservices.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ies.spd.spdservices.constants.Constants;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Athor: 吴广庆
 * @Date: 2019-07-24
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = Constants.TABLE_PREFIX +"area")
public class VArea implements Serializable{

    private Long id;
    private String name;
    private String code;
    private String parentCode;
    private VArea areaParent;
    private List<VArea> areaChildren;
    private List<Equipment> equipmentList;
    private String longitude;
    private String latitude;
    private Date updateTime;
    private Boolean isDelete;
    private Integer rootCount;

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
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @ManyToOne
    @JoinColumn(name = "P_CODE",referencedColumnName = "CODE")
    @JsonBackReference(value = "areaParent")
    public VArea getAreaParent() {
        return areaParent;
    }

    public void setAreaParent(VArea areaParent) {
        this.areaParent = areaParent;
    }

    @Transient
    public String getParentCode() {
        if(areaParent != null){
            parentCode = areaParent.getCode();
        }
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @OneToMany(cascade = CascadeType.REFRESH,mappedBy = "areaParent")
    public List<VArea> getAreaChildren() {
        return areaChildren;
    }

    public void setAreaChildren(List<VArea> areaChildren) {
        this.areaChildren = areaChildren;
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @OneToMany(cascade = CascadeType.REFRESH,mappedBy = "area")
    @JsonIgnore
    public List<Equipment> getEquipmentList() {
        return equipmentList;
    }

    public void setEquipmentList(List<Equipment> equipmentList) {
        this.equipmentList = equipmentList;
    }

    @Column(name = "ITUDE_LONG")
    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Column(name = "ITUDE_LAT")
    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    @Column(name = "UPDATE_DATE")
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Column(name = "IS_DELETE",updatable = false)
    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    @Column(name = "ROOT_COUNT",updatable = false)
    public Integer getRootCount() {
        return rootCount;
    }

    public void setRootCount(Integer rootCount) {
        this.rootCount = rootCount;
    }
}
