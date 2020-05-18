package com.tanda.tdshop.entities.tdshop;
import java.util.Date;
import java.sql.*;
import lombok.Data;

/**
 * t_user_coupon  generated at 2020-05-15 14:17:58 by: cxm
 */@Data

public class UserCoupon{
	private String wx_openid;
	private int coupon_id;
	private Timestamp get_time;
	private Timestamp use_time;
	private boolean use_status;
	private Date update_time;
	private Date create_time;

}
