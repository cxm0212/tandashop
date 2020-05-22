package com.tanda.tdshop;

import com.tanda.tdshop.entities.tdshop.UserEntity;
import com.tanda.tdshop.manager.modules.login.LoginService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TdShopApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TdShopApplicationTests {

    @Autowired
    private LoginService loginService;

    @Test
    void contextLoads() {
        UserEntity userEntity = loginService.selectById(1);
        System.out.println(userEntity);
    }

}
