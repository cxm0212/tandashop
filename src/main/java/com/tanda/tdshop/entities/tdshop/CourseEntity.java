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
	private Integer categoryId;
	private Float price;
	private Float vipPrice;
	private Byte state;
	private Byte isHot;
	private Byte isSeckill;
	private Integer browse;
	private Byte isGroup;
	private Float groupPrice;
	private Integer groupUsernum;
	private Timestamp groupStartTime;
	private Timestamp groupEndTime;
	private Integer mixAgeUse;
	private Integer maxAgeUse;
	private Integer createUserId;
	private String courseMiniImg;

}
