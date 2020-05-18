package com.tanda.tdshop.entities.tdshop;
import java.util.Date;
import lombok.Data;
import com.tanda.tdshop.common.BaseEntity;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * t_course_video  generated at 2020-05-15 15:43:30 by: cxm
 */
@Data
@TableName("t_course_video" )
public class CourseVideoEntity extends BaseEntity{
	private int courseId;
	private String tryvideoUrl;
	private String fullVideoUrl;

}
