package com.tanda.tdshop.manager.modules.login;

import lombok.Data;

/**
 * create by
 * 三和智控: cxm on 2020/5/14
 */

@Data
public class UserLoginFormDTO {

    private String loginName;
    private String loginPassword;
    private String codeInputValue;
    private String codeText;
}
