package com.tanda.tdshop.manager.enums;

/**
 * create by
 * 三和智控: cxm on 2020/5/14
 */
public enum  ResponseCodeEnum {

    NO(0, "否"),

    YES(1, "是"),
    ;


    public Integer getValue() {
        return value;
    }
    public String getDesc() {
        return desc;
    }

    private Integer value;
    private String desc;

    ResponseCodeEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }


}
