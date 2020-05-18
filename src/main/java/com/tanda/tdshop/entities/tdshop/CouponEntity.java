package com.tanda.tdshop.entities.tdshop;
import java.util.Date;
import lombok.Data;
import com.tanda.tdshop.common.BaseEntity;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * t_coupon  generated at 2020-05-15 15:43:30 by: cxm
 */
@Data
@TableName("t_coupon" )
public class CouponEntity extends BaseEntity{
	private double price;
	private int useDate;
	private double minPrice;
	private int typeId;
	private boolean status;

}
