package com.tanda.tdshop.entities.tdshop;
import java.util.Date;
import lombok.Data;
import com.tanda.tdshop.common.BaseEntity;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * t_groupbuy_order  generated at 2020-05-15 15:43:30 by: cxm
 */
@Data
@TableName("t_groupbuy_order" )
public class GroupbuyOrderEntity extends BaseEntity{
	private int orderId;
	private String wxOpenid;
	private int courseId;
	private boolean isInitiator;

}
