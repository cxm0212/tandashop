package com.tanda.tdshop.entities.tdshop;
import java.util.Date;
import java.sql.*;
import lombok.Data;
import com.tanda.tdshop.common.BaseEntity;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * t_user_coupon  generated at 2020-05-15 15:43:30 by: cxm
 */
@Data
@TableName("t_user_coupon" )
public class UserCouponEntity extends BaseEntity{
	private String wxOpenid;
	private int couponId;
	private Timestamp getTime;
	private Timestamp useTime;
	private boolean useStatus;

}
