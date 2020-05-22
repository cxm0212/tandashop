package com.tanda.tdshop.manager.modules.course.dto;

import lombok.Data;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

/**
 * create by
 * 三和智控: cxm on 2020/5/18
 */
@Data
public class CourseDTO {

    private Long id;
    private Integer categoryId;
    private String title;
    private String desc;
    private byte isline;
    private Float price;
    private byte ishot;
    private Integer minAge;
    private Integer maxAge;
    private byte isgroup;
    private Float groupPrice;
    private Integer groupNum;



}

