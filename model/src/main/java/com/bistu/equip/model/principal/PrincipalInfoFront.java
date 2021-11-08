package com.bistu.equip.model.principal;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.bistu.equip.model.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author Dx666
 * @Description
 * @Date 2021/11/5 - 22:14
 */
@Data
@TableName("front_principal")
public class PrincipalInfoFront extends BaseEntity {
	
	@ApiModelProperty("后台记录编号")
	@TableField("pri_id")
	private Long principalId;
	
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
	@TableField("username")
	private String username;
	
	@ApiModelProperty("设备名")
	@TableField("equip_name")
	private String equipName;
	
	@ApiModelProperty("借出时间")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@TableField(value = "lend_time", fill = FieldFill.INSERT)
	private Date lendTime;
	
	@ApiModelProperty("返还时间")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@TableField(value = "estimate_return_time", fill = FieldFill.INSERT)
	private Date estimateReturnTime;
	
	@ApiModelProperty("预计返还时间")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@TableField(value = "actual_return_time")
	private Date actualReturnTime;
	
	@ApiModelProperty("借用时长")
	private Integer borrowTime;
	
	@ApiModelProperty("归还倒计时")
	private Integer returnCount;
	
}
