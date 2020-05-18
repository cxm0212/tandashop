package com.tanda.tdshop.entities.tdshop;
import java.util.Date;
import lombok.Data;
import com.tanda.tdshop.common.BaseEntity;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * t_user  generated at 2020-05-15 15:43:30 by: cxm
 */
@Data
@TableName("t_user" )
public class UserEntity extends BaseEntity{
	private String name;
	private String password;
	private boolean type;

}
