package com.tanda.tdshop.entities.tdshop;
import java.util.Date;
import java.sql.*;
import lombok.Data;

/**
 * t_order  generated at 2020-05-15 14:17:58 by: cxm
 */@Data

public class Order{
	private int id;
	private String order_num;
	private int course_id;
	private String wx_openid;
	private boolean state;
	private boolean refund_status;
	private double price;
	private int coupon_id;
	private double coupon_price;
	private double real_price;
	private Timestamp pay_time;
	private Timestamp refund_time;
	private double refund_price;
	private String refund_reason;
	private int groupbuy_id;
	private Timestamp create_time;
	private Timestamp end_time;
	private Date update_time;

}
