package com.tanda.tdshop.entities.tdshop;
import java.util.Date;
import java.sql.*;
import lombok.Data;

/**
 * t_course  generated at 2020-05-15 14:17:58 by: cxm
 */@Data

public class Course{
	private int id;
	private String title;
	private String description;
	private int category_id;
	private double price;
	private double vip_price;
	private boolean state;
	private boolean is_hot;
	private boolean is_seckill;
	private int browse;
	private boolean is_group;
	private double group_price;
	private int group_usernum;
	private Timestamp group_start_time;
	private Timestamp group_end_time;
	private int mix_age_use;
	private int max_age_use;
	private int create_user_id;
	private String course_mini_img;
	private Date update_time;
	private Date create_time;

}
