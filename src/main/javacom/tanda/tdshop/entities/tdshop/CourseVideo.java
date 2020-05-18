package com.tanda.tdshop.entities.tdshop;
import java.util.Date;
import lombok.Data;

/**
 * t_course_video  generated at 2020-05-15 14:17:58 by: cxm
 */@Data

public class CourseVideo{
	private int id;
	private int course_id;
	private String tryvideo_url;
	private String full_video_url;
	private Date update_time;
	private Date create_time;

}
