package com.tanda.tdshop.manager.modules.course;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.tanda.tdshop.entities.tdshop.CourseEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * create by
 * 三和智控: cxm on 2020/5/15
 */
@Mapper
@Component
public interface CourseDao extends BaseMapper<CourseEntity> {


    List<CourseEntity> selectAll(@Param("courseId") Long courseId);

    @Update("update t_course set state=0 where id=#{courseId}")
    void deleteCourse(Integer courseId);
}
