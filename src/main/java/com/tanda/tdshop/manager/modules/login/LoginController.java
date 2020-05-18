package com.tanda.tdshop.manager.modules.login;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * create by
 * 三和智控: cxm on 2020/5/13
 */
@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping(value = "/getcode")
    private JSONObject getCode(){
        JSONObject object =  loginService.getCode();
        return object;
    }

    @RequestMapping("/")
    private JSONObject login(@RequestBody UserLoginFormDTO loginFormDTO, HttpServletRequest request){
        JSONObject object =  loginService.login(loginFormDTO,request);

        return object;
    }

}
