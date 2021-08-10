package com.bistu.equip.model.user;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;


import com.bistu.equip.model.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;


/**
 * @author Dx666
 * @Description
 * @Date 2021/7/30 - 20:58
 */
@Data
@TableName("user")
@ApiModel(description = "用户表")
public class UserInfo extends BaseEntity {
	
	@ApiModelProperty("微信编号")
	@TableField("wx_id")
	private String wxId;
	
	@ApiModelProperty("用户名")
	@TableField("name")
	private String name;
	
	@ApiModelProperty("性别")
	@TableField("sex")
	private Integer sex;
	
	@ApiModelProperty("电话号码")
	@TableField("phone")
	private String phone;
	
	@ApiModelProperty("身份（0：学生 1：老师）")
	@TableField("identity")
	private Integer identity;
	
	@ApiModelProperty("账户类型 (0：普通用户 1：管理员)")
	@TableField("account_type")
	private Integer accountType;
	
	@ApiModelProperty("学院")
	@TableField("college")
	private String college;
	
	@ApiModelProperty("工号/学号")
	@TableField("stu_or_tec_id")
	private String no;
	
	@ApiModelProperty("系部")
	@TableField("dept")
	private String dept;
	
	@ApiModelProperty("专业")
	@TableField("major")
	private String major;
	
	@ApiModelProperty("年级")
	@TableField("grade")
	private String grade;
	
	@ApiModelProperty("职称")
	@TableField("position")
	private String position;
	
	@ApiModelProperty("班级")
	@TableField("class")
	private String schoolClass;
	
	@ApiModelProperty("校内邮箱")
	@TableField("school_email")
	private String schoolEmail;
	
	@ApiModelProperty("校内邮箱")
	@TableField("usual_email")
	private String usualEmail;
	
	@ApiModelProperty("状态(0：禁用 1：未禁用)")
	@TableField("status")
	private Integer status;
	
	@ApiModelProperty("上次登录时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@TableField("last_login_time")
	private Date  lastLoginTime;
}