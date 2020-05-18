package com.tanda.tdshop.manager.modules.course;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * create by
 * 三和智控: cxm on 2020/5/15
 */
@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @RequestMapping("/addcategory")
    public JSONObject addCategory(@RequestBody CategoryDTO categoryDTO, HttpServletRequest request){
        return courseService.insetCategory(categoryDTO,request);
    }

    @RequestMapping("/getcategorys")
    public Object getCategorys(HttpServletRequest request){
        return courseService.getCategorys(request);
    }
}
