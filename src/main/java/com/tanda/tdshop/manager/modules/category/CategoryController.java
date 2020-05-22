package com.tanda.tdshop.manager.modules.category;

import com.alibaba.fastjson.JSONObject;
import com.tanda.tdshop.consts.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * create by
 * 三和智控: cxm on 2020/5/21
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @RequestMapping("/addcategory")
    public ResponseDTO<CategoryDTO> addCategory(@RequestBody CategoryDTO categoryDTO, HttpServletRequest request){
        return categoryService.insetCategory(categoryDTO,request);
    }

    @RequestMapping("/getcategorys")
    public ResponseDTO<CategoryDTO> getCategorys(HttpServletRequest request){
        return categoryService.getCategorys(request);
    }

    @RequestMapping("/updatecategory")
    public ResponseDTO<CategoryDTO> updateCategory(@RequestBody CategoryDTO categoryDTO,HttpServletRequest request){
        return categoryService.updateCategory(categoryDTO,request);
    }

    @RequestMapping("/deletecategory")
    public ResponseDTO<Object> deleteCategory(@RequestBody JSONObject json, HttpServletRequest request){
        return categoryService.deleteCategory(json,request);
    }
}
