package com.tanda.tdshop.manager.modules.login;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.tanda.tdshop.entities.tdshop.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * create by
 * 三和智控: cxm on 2020/5/13
 */

@Mapper
@Component
public interface LoginDao extends BaseMapper<UserEntity> {


    @Select("select * from t_user")
    UserEntity selectAll();

    @Select("select * from t_user where name=#{loginName} and password=#{loginPassword}" )
    UserEntity getUser(@Param("loginName") String loginName,@Param(("loginPassword")) String loginPassword);
}
