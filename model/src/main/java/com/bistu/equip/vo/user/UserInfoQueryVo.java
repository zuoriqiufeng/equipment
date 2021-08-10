package com.bistu.equip.vo.user;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Dx666
 * @Description
 * @Date 2021/7/31 - 16:00
 */
@Data
@ApiModel(description = "用户查询对象")
public class UserInfoQueryVo {
	
	@ApiModelProperty(value = "状态")
	private Integer status;
	
	@ApiModelProperty("身份（0：学生 1：老师）")
	private Integer identity;
	
	@ApiModelProperty("账户类型 (0：普通用户 1：管理员)")
	private Integer accountType;
	
	@ApiModelProperty("学院")
	private String college;
	
	@ApiModelProperty("工号/学号")
	private String no;
	
	@ApiModelProperty("系部")
	private String dept;
	
	@ApiModelProperty("专业")
	private String major;
	
}