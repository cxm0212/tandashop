package com.tanda.tdshop.manager.modules.course;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tanda.tdshop.entities.tdshop.CourseCategoryEntity;
import com.tanda.tdshop.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * create by
 * 三和智控: cxm on 2020/5/15
 */
@Service
public class CourseService {

    @Autowired
    private CourseDao courseDao;

    @Autowired
    private CourseCategoryDao categoryDao;

    public JSONObject insetCategory(CategoryDTO categoryDTO, HttpServletRequest request) {
//        System.out.println(request.getParameterMap());
//        String categoryName = request.getParameter("categoryName");

        CourseCategoryEntity categoryEntity = new CourseCategoryEntity();
        categoryEntity.setTitle(categoryDTO.getCategoryName());
        CourseCategoryEntity categoryEntity1 = categoryDao.selectOne(categoryEntity);

        JSONObject object = new JSONObject();
        if(categoryEntity1==null){
            categoryEntity.setUpdateTime(new Date());
            categoryEntity.setCreateTime(new Date());

            categoryDao.insert(categoryEntity);
            object.put("status",2001);
            object.put("msg","添加成功!");
            object.put("data",categoryEntity);
        }else{
            object.put("status",2000);
            object.put("msg","类别已存在!");
        }
        return object;
    }


    public Object getCategorys(HttpServletRequest request) {
        String categoryId = request.getParameter("id");

        List<CourseCategoryEntity> list = categoryDao.selectAll(categoryId);
        List<CategoryDTO> dtoList = new ArrayList<>();
        CategoryDTO categoryDTO =null;
        int i=0;
        for (CourseCategoryEntity categoryEntity :list) {
            categoryDTO = new CategoryDTO();
            categoryDTO.setId(categoryEntity.getId());
            categoryDTO.setCategoryName(categoryEntity.getTitle());
            categoryDTO.setKey(++i);
            categoryDTO.setCreateTime(DateUtil.formatYMDHMS(categoryEntity.getCreateTime()));
            categoryDTO.setUpdateTime(DateUtil.formatYMDHMS(categoryEntity.getUpdateTime()));

            dtoList.add(categoryDTO);
        }
        return dtoList;
    }
}
