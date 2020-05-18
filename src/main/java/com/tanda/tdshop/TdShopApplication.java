package com.tanda.tdshop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
@SpringBootApplication
public class TdShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(TdShopApplication.class, args);
    }

    /**
     * 访问首页提示
     * @return /
     */
//    @GetMapping("/")
//    public String index() { return "Backend service started successfully"; }
}
