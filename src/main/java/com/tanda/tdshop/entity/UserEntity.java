package com.tanda.tdshop.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.tanda.tdshop.common.BaseEntity;
import lombok.Data;

/**
 * create by
 * 三和智控: cxm on 2020/5/13
 */
@Data
@TableName("t_user")
public class UserEntity extends BaseEntity {

    private String name;
    private String password;
    private int type;


}
