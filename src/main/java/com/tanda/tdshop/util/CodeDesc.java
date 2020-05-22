package com.tanda.tdshop.util;

import com.tanda.tdshop.manager.enums.ResponseCodeEnum;

/**
 * create by
 * 三和智控: cxm on 2020/5/19
 */
public class CodeDesc {

    public static String getCodeDesc(int code){
        if(code== ResponseCodeEnum.NO.getValue()){
            return ResponseCodeEnum.NO.getDesc();
        }
        if(code== ResponseCodeEnum.YES.getValue()){
            return ResponseCodeEnum.YES.getDesc();
        }

        return null;
    }
}
