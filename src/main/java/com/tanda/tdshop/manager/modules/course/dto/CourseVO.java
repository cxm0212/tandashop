package com.tanda.tdshop.manager.modules.course.dto;

import com.tanda.tdshop.entities.tdshop.CourseCategoryEntity;
import lombok.Builder;
import lombok.Data;

/**
 * create by
 * 三和智控: cxm on 2020/5/19
 */
@Data
@Builder
public class CourseVO {

    private Long id;
    private Integer categoryId;
    private String categorydesc;
    private String title;
    private String desc;
    private String isline;
    private Float price;
    private String ishot;
    private Integer minAge;
    private Integer maxAge;
    private String isgroup;
    private Float groupPrice;
    private Integer groupNum;
    private Long key;
}
