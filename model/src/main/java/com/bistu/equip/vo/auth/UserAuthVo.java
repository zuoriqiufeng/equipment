package com.bistu.equip.vo.auth;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Dx666
 * @Description
 * @Date 2021/8/22 - 18:08
 */
@Data
public class UserAuthVo {
	@ApiModelProperty("姓名")
	private String name;
	
	@ApiModelProperty("性别")
	private Integer sex;
	
	@ApiModelProperty("电话号码")
	private String phone;
	
	@ApiModelProperty("学院")
	private String college;
	
	@ApiModelProperty("工号/学号")
	private String no;
	
}
