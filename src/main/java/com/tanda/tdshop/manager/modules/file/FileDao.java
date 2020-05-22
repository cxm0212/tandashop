package com.tanda.tdshop.manager.modules.file;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.tanda.tdshop.entities.tdshop.CourseImgEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * create by
 * 三和智控: cxm on 2020/5/19
 */
@Mapper
@Component
public interface FileDao extends BaseMapper<CourseImgEntity> {

    @Select("select * from t_course_img where course_id=#{courseid}")
    List<CourseImgEntity> selectByCourseId(int courseId);
}
