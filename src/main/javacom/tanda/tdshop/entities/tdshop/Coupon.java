package com.tanda.tdshop.entities.tdshop;
import java.util.Date;
import lombok.Data;

/**
 * t_coupon  generated at 2020-05-15 14:05:58 by: cxm
 */@Data

public class Coupon{
	private int id;
	private double price;
	private int use_date;
	private double min_price;
	private int type_id;
	private boolean status;
	private Date update_time;
	private Date create_time;

}
