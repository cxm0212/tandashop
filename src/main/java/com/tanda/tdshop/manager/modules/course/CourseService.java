package com.tanda.tdshop.manager.modules.course;

import com.alibaba.fastjson.JSONObject;
import com.tanda.tdshop.consts.ResponseCodeConst;
import com.tanda.tdshop.consts.ResponseDTO;
import com.tanda.tdshop.entities.tdshop.CourseCategoryEntity;
import com.tanda.tdshop.entities.tdshop.CourseEntity;
import com.tanda.tdshop.manager.modules.category.CategoryDTO;
import com.tanda.tdshop.manager.modules.category.CourseCategoryDao;
import com.tanda.tdshop.manager.modules.course.dto.CourseDTO;
import com.tanda.tdshop.manager.modules.course.dto.CourseVO;
import com.tanda.tdshop.util.CodeDesc;
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


    public ResponseDTO<CourseDTO> addCourse(CourseDTO courseDTO, HttpServletRequest request) {

        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setCategoryId(courseDTO.getCategoryId());
        courseEntity.setTitle(courseDTO.getTitle());
        courseEntity.setDescription(courseDTO.getDesc());
        courseEntity.setState(courseDTO.getIsline());
        courseEntity.setPrice(courseDTO.getPrice());
        courseEntity.setIsHot(courseDTO.getIshot());
        courseEntity.setMixAgeUse(courseDTO.getMinAge());
        courseEntity.setMaxAgeUse(courseDTO.getMaxAge());
        courseEntity.setIsGroup(courseDTO.getIsgroup());
        if(courseDTO.getIsgroup()==1){
            courseEntity.setGroupPrice(courseDTO.getGroupPrice());
            courseEntity.setGroupUsernum(courseDTO.getGroupNum());
        }
        Integer insert=null;

        if(courseDTO.getId()!=null && courseDTO.getId()>0){
            //更新
            courseEntity.setId(courseDTO.getId());
            insert = courseDao.updateById(courseEntity);
            return  ResponseDTO.wrapSuccessData(ResponseCodeConst.UPDATE_SUCCESS,courseDTO);
        }else{
            insert = courseDao.insert(courseEntity);
            courseDTO.setId(courseEntity.getId());
            return  ResponseDTO.wrapSuccessData(ResponseCodeConst.ADD_SUCCESS,courseDTO);
        }

    }

    public ResponseDTO<CourseVO> getCourses(JSONObject json, HttpServletRequest request) {
        Long courseId =json.getLong("courseId");
        String type = json.getString("type");

        List<CourseEntity> courseEntities = courseDao.selectAll(courseId);
        List<CourseVO> list = new ArrayList<>();
        CourseVO courseVO = null;
        for (CourseEntity courseEntity : courseEntities) {
            courseVO = CourseVO.builder()
                    .id(courseEntity.getId())
                    .title(courseEntity.getTitle())
                    .desc(courseEntity.getDescription())
                    .price(courseEntity.getPrice())
                    .isline(CodeDesc.getCodeDesc(courseEntity.getState()))
                    .ishot(CodeDesc.getCodeDesc(courseEntity.getIsHot()))
                    .minAge(courseEntity.getMixAgeUse())
                    .maxAge(courseEntity.getMaxAgeUse())
                    .groupPrice(courseEntity.getGroupPrice())
                    .groupNum(courseEntity.getGroupUsernum())
                    .categoryId(courseEntity.getCategoryId())
                    .categorydesc(((CourseCategoryEntity)categoryDao.selectById(courseEntity.getCategoryId())).getTitle())
                    .key(courseEntity.getId()).build();
            if("update".equals(type)){
                courseVO.setIsline(courseEntity.getState().toString());
                courseVO.setIshot(courseEntity.getIsHot().toString());
                courseVO.setIsgroup(courseEntity.getIsGroup().toString());
            }
            list.add(courseVO);
        }
        return ResponseDTO.wrapSuccessData(ResponseCodeConst.SELECT_SUCCESS,list);
    }





    public ResponseDTO<Object> deletecourse(JSONObject json, HttpServletRequest request) {
        Integer courseId = json.getInteger("id");
        courseDao.deleteCourse(courseId);
        return ResponseDTO.wrapCode(ResponseCodeConst.DELETE_SUCCESS);

    }


}
