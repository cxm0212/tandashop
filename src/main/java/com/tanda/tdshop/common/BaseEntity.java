package com.tanda.tdshop.common;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * create by
 * 三和智控: cxm on 2020/5/13
 */
@Data
public class BaseEntity {

    @TableId(type= IdType.AUTO)
    private Long id;
    private Date updateTime;
    private Date createTime;
}
