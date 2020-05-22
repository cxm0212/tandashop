package com.tanda.tdshop.consts;

import lombok.Data;

/**
 * create by
 * 三和智控: cxm on 2020/5/19
 */
@Data
public class ResponseCodeConst {


    private int code;
    private String message;

    public static final ResponseCodeConst ADD_SUCCESS = new ResponseCodeConst(2001,"添加成功!");
    public static final ResponseCodeConst UPDATE_SUCCESS = new ResponseCodeConst(2002,"更新成功!");
    public static final ResponseCodeConst DELETE_SUCCESS = new ResponseCodeConst(2003,"删除成功!");
    public static final ResponseCodeConst CATEGORY_EXIST = new ResponseCodeConst(2000,"类别已存在!");
    public static final ResponseCodeConst SELECT_SUCCESS = new ResponseCodeConst(1,"查询成功!");



    public ResponseCodeConst(int code, String message) {
        this.code = code;
        this.message = message;
    }


}

