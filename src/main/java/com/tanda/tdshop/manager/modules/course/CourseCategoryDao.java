package com.tanda.tdshop.manager.modules.course;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.tanda.tdshop.entities.tdshop.CourseCategoryEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * create by
 * 三和智控: cxm on 2020/5/15
 */
@Mapper
@Component
public interface CourseCategoryDao extends BaseMapper<CourseCategoryEntity> {

    List<CourseCategoryEntity> selectAll(String categoryId);
}
