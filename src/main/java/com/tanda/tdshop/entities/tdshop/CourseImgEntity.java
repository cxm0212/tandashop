package com.tanda.tdshop.entities.tdshop;
import java.util.Date;
import lombok.Data;
import com.tanda.tdshop.common.BaseEntity;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * t_course_img  generated at 2020-05-15 15:43:30 by: cxm
 */
@Data
@TableName("t_course_img" )
public class CourseImgEntity extends BaseEntity{
	private String img;
	private boolean type;
	private int displayOrder;

}
