package com.tanda.tdshop.manager.modules.category;

import com.alibaba.fastjson.JSONObject;
import com.tanda.tdshop.consts.ResponseCodeConst;
import com.tanda.tdshop.consts.ResponseDTO;
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
 * 三和智控: cxm on 2020/5/21
 */
@Service
public class CategoryService {

    @Autowired
    private CourseCategoryDao categoryDao;





    public ResponseDTO<CategoryDTO> insetCategory(CategoryDTO categoryDTO, HttpServletRequest request) {
//        System.out.println(request.getParameterMap());
//        String categoryName = request.getParameter("categoryName");

        CourseCategoryEntity categoryEntity = new CourseCategoryEntity();
        categoryEntity.setTitle(categoryDTO.getCategoryName());
        CourseCategoryEntity categoryEntity1 = categoryDao.selectOne(categoryEntity);

        JSONObject object = new JSONObject();
        if(categoryEntity1==null){
            categoryEntity.setState(1);
            categoryEntity.setUpdateTime(new Date());
            categoryEntity.setCreateTime(new Date());

            categoryDao.insert(categoryEntity);
//            object.put("status",2001);
//            object.put("msg","添加成功!");
//            object.put("data",categoryEntity);

            categoryDTO.setId(categoryEntity.getId());

            return  ResponseDTO.wrapSuccessData(ResponseCodeConst.ADD_SUCCESS,categoryDTO);

        }else{
            object.put("status",2000);
            object.put("msg","类别已存在!");
            return  ResponseDTO.wrapCode(ResponseCodeConst.CATEGORY_EXIST);
        }
    }


    public ResponseDTO<CategoryDTO> getCategorys(HttpServletRequest request) {
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
        return ResponseDTO.wrapSuccessData(ResponseCodeConst.SELECT_SUCCESS,dtoList);
    }

    public ResponseDTO<CategoryDTO> updateCategory(CategoryDTO categoryDTO, HttpServletRequest request) {
        CourseCategoryEntity categoryEntity = new CourseCategoryEntity();
        categoryEntity.setId(categoryDTO.getId());
        categoryEntity.setTitle(categoryDTO.getCategoryName());
        categoryEntity.setUpdateTime(new Date());
        categoryDao.updateById(categoryEntity);
        return ResponseDTO.wrapSuccessData(ResponseCodeConst.UPDATE_SUCCESS,categoryEntity);
    }
    public ResponseDTO<Object> deleteCategory(JSONObject json, HttpServletRequest request) {
        Long categoryId = json.getLong("id");
        categoryDao.updateCategory(categoryId);
        return ResponseDTO.wrapCode(ResponseCodeConst.DELETE_SUCCESS);
    }


}
