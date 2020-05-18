package com.tanda.tdshop.manager.modules.login;

import com.alibaba.fastjson.JSONObject;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.tanda.tdshop.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

/**
 * create by
 * 三和智控: cxm on 2020/5/13
 */
@Slf4j
@Service
public class LoginService {


    @Autowired
    private LoginDao loginDao;
    @Autowired
    private DefaultKaptcha defaultKaptcha;

    public UserEntity selectById(int i) {
        System.out.println("-------------------------------");
        return loginDao.selectById(i);
    }

    public JSONObject getCode() {
        String base64Code = null;
        String text = defaultKaptcha.createText();
        BufferedImage image = defaultKaptcha.createImage(text);
        ByteArrayOutputStream outputStream = null;
        try {
            outputStream = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", outputStream);
            base64Code = "data:image/png;base64,"+ Base64.encodeBase64String(outputStream.toByteArray());
        } catch (Exception e) {
            log.error("verificationCode exception .{}", e);
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (Exception e) {
                    log.error("verificationCode outputStream close exception .{}", e);
                }
            }
        }

        JSONObject object = new JSONObject();
        object.put("code",base64Code);
        object.put("codeText",text);
        return object;
    }

    public JSONObject login(UserLoginFormDTO loginFormDTO, HttpServletRequest request) {
       JSONObject object = new JSONObject();
       UserEntity user = loginDao.getUser(loginFormDTO.getLoginName(),loginFormDTO.getLoginPassword());
       if(user==null){
           object.put("status",1000);
           object.put("msg","用户名或密码错误!");
       }else{
           object.put("status",1001);
           object.put("msg","success");
           object.put("data",user);
       }
       return object;

    }
}
