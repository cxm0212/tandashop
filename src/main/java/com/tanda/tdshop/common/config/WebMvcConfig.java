package com.tanda.tdshop.common.config;

import com.tanda.tdshop.manager.interceptor.TDInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置
 * create by
 * 三和智控: cxm on 2020/5/14
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(new TDInterceptor());
        interceptorRegistration.addPathPatterns("/**");                      //所有路径都被拦截
//        interceptorRegistration.excludePathPatterns(                         //添加不拦截路径
//                "你的登陆路径",            //登录
//                "/**/*.html",            //html静态资源
//                "/**/*.js",              //js静态资源
//                "/**/*.css",             //css静态资源
//                "/**/*.woff",
//                "/**/*.ttf"
//        );
    }
}
