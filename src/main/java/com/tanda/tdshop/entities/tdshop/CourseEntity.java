package com.tanda.tdshop.entities.tdshop;
import java.util.Date;
import java.sql.*;
import lombok.Data;
import com.tanda.tdshop.common.BaseEntity;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * t_course  generated at 2020-05-15 15:43:30 by: cxm
 */
@Data
@TableName("t_course" )
public class CourseEntity extends BaseEntity{
	private String title;
	private String description;
	private int categoryId;
	private double price;
	private double vipPrice;
	private boolean state;
	private boolean isHot;
	private boolean isSeckill;
	private int browse;
	private boolean isGroup;
	private double groupPrice;
	private int groupUsernum;
	private Timestamp groupStartTime;
	private Timestamp groupEndTime;
	private int mixAgeUse;
	private int maxAgeUse;
	private int createUserId;
	private String courseMiniImg;

}
