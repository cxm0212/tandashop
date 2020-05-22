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
    private Long courseId;
	private String relativePath;
	private String absolutePath;
	private String oldName;
	private String newName;
	private int type;
	private int displayOrder;
	private Long fileSize;

}
