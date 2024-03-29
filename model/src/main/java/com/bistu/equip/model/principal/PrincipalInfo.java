package com.bistu.equip.model.principal;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.bistu.equip.model.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Dx666
 * @Description
 * @Date 2021/7/30 - 21:23
 */
@Data
@ApiModel(description = "equip_principal")
@TableName("equip_principal")
public class PrincipalInfo extends BaseEntity {
	
	@ApiModelProperty("用户编号")
	@TableField("uid")
	private Long uid;
	
	@ApiModelProperty("设备号")
	@TableField("equip_id")
	private Long equipId;
	
	@ApiModelProperty("是否归还（0：未归还，1：已归还）")
	@TableField("status")
	private Integer status;
	
	@ApiModelProperty("用户名")
	@TableField("user_name")
	private String username;
	
	@ApiModelProperty("设备名")
	@TableField("equip_name")
	private String equipName;
	
	
	@ApiModelProperty("借出经办人")
	@TableField("lend_human")
	private String lendHuman;
	
	@ApiModelProperty("老师姓名")
	@TableField("teacher_name")
	private String tecName;
	
	
	@ApiModelProperty("返还经办人")
	@TableField("re_human")
	private String reHuman;
	
	@ApiModelProperty("返还人")
	@TableField("re_user")
	private String reUsername;
	
	@ApiModelProperty("借出时间")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@TableField(value = "lend_time", fill = FieldFill.INSERT)
	private Date lendTime;
	
	@ApiModelProperty("预计返还时间")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@TableField("estimate_return_time")
	private Date estimateReturnTime;
	
	@ApiModelProperty("实际返还时间")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@TableField("actual_return_time")
	private Date actualReturnTime;
	
	
	@ApiModelProperty("借用时长")
	@TableField(value = "borrow_time")
	private Integer borrowTime;
	
	
	

}
