package com.tanda.tdshop.entities.tdshop;
import java.util.Date;
import java.sql.*;
import lombok.Data;
import com.tanda.tdshop.common.BaseEntity;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * t_order  generated at 2020-05-15 15:43:30 by: cxm
 */
@Data
@TableName("t_order" )
public class OrderEntity extends BaseEntity{
	private String orderNum;
	private int courseId;
	private String wxOpenid;
	private boolean state;
	private boolean refundStatus;
	private double price;
	private int couponId;
	private double couponPrice;
	private double realPrice;
	private Timestamp payTime;
	private Timestamp refundTime;
	private double refundPrice;
	private String refundReason;
	private int groupbuyId;
	private Timestamp endTime;

}
