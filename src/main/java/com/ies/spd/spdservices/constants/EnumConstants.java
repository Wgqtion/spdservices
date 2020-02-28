package com.ies.spd.spdservices.constants;

/**
 * @Athor: 吴广庆
 * @Date: 2019-06-28
 */
public enum EnumConstants {
    SPT("/"), SYS_NAME("上海宜事智能停车管理平台");
    private String value;


    private EnumConstants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
