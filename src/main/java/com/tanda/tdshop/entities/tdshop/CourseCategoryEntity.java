package com.tanda.tdshop.entities.tdshop;
import java.util.Date;
import lombok.Data;
import com.tanda.tdshop.common.BaseEntity;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * t_course_category  generated at 2020-05-15 15:43:30 by: cxm
 */
@Data
@TableName("t_course_category" )
public class CourseCategoryEntity extends BaseEntity{
	private String title;

}
