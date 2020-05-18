package com.tanda.tdshop.entities.tdshop;
import java.util.Date;
import lombok.Data;

/**
 * t_groupbuy_order  generated at 2020-05-15 14:17:58 by: cxm
 */@Data

public class GroupbuyOrder{
	private int id;
	private int order_id;
	private String wx_openid;
	private int course_id;
	private boolean is_initiator;
	private Date update_time;
	private Date create_time;

}
