package com.tanda.tdshop.entities.tdshop;
import java.util.Date;
import lombok.Data;

/**
 * t_user  generated at 2020-05-15 14:17:58 by: cxm
 */@Data

public class User{
	private int id;
	private String name;
	private String password;
	private boolean type;
	private Date update_time;
	private Date create_time;

}
