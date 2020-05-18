package com.tanda.tdshop.manager.modules.course;

import lombok.Data;

import java.util.Date;

/**
 * create by
 * 三和智控: cxm on 2020/5/15
 */
@Data
public class CategoryDTO {

    private Long id;
    private String categoryName;
    private int key;
    private String updateTime;
    private String createTime;
}
