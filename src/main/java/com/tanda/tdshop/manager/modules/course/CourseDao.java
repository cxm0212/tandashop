package com.tanda.tdshop.manager.modules.course;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.tanda.tdshop.entity.CourseEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * create by
 * 三和智控: cxm on 2020/5/15
 */
@Mapper
@Component
public interface CourseDao extends BaseMapper<CourseEntity> {


}
