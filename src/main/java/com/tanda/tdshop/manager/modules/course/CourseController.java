package com.tanda.tdshop.manager.modules.course;

import com.alibaba.fastjson.JSONObject;
import com.tanda.tdshop.consts.ResponseDTO;
import com.tanda.tdshop.manager.modules.category.CategoryDTO;
import com.tanda.tdshop.manager.modules.course.dto.CourseDTO;
import com.tanda.tdshop.manager.modules.course.dto.CourseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping("/addcourse")
    public ResponseDTO<CourseDTO> addCourse(@RequestBody  CourseDTO courseDTO, HttpServletRequest request){
        return courseService.addCourse(courseDTO,request);
    }

    @RequestMapping("/getcourselist")
    public ResponseDTO<CourseVO> getCourses(@RequestBody JSONObject json,HttpServletRequest request){
        return courseService.getCourses(json,request);
    }



    @RequestMapping("/deletecourse")
    public ResponseDTO<Object> deleteCourse(@RequestBody JSONObject json, HttpServletRequest request){
        return courseService.deletecourse(json,request);
    }

}
