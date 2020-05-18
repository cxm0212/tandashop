package com.tanda.tdshop.entities.tdshop;
import java.util.Date;
import java.sql.*;
import lombok.Data;

/**
 * t_user_course  generated at 2020-05-15 14:17:58 by: cxm
 */@Data

public class UserCourse{
	private String wx_openid;
	private int course_id;
	private null view_time;
	private Timestamp lastview_time;
	private Date update_time;
	private Date create_time;

}
