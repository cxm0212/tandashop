package com.tanda.tdshop.consts;

import lombok.Data;

/**
 * create by
 * 三和智控: cxm on 2020/5/19
 */
@Data
public class ResponseDTO<T>{
    protected Integer code;

    protected String message;

//    protected Boolean success;

    protected T data;


    public static <T> ResponseDTO wrapCode(ResponseCodeConst codeConst){
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setCode(codeConst.getCode());
        responseDTO.setMessage(codeConst.getMessage());
        return responseDTO;
    }

    public static <T> ResponseDTO wrapSuccessData(ResponseCodeConst codeConst,T data){
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setCode(codeConst.getCode());
        responseDTO.setMessage(codeConst.getMessage());
        responseDTO.setData(data);
        return responseDTO;
    }
}