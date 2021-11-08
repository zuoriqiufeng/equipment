package com.bistu.equip.vo.principal;

import com.baomidou.mybatisplus.annotation.TableField;
import com.bistu.equip.model.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author Dx666
 * @Description
 * @Date 2021/11/5 - 22:36
 */
@Data
public class PrincipalInfoBack extends BaseEntity {
	
	@ApiModelProperty("用户编号")
	private Long uid;
	
	@ApiModelProperty("设备号")
	private Long equipId;
	
	@ApiModelProperty("是否归还（0：未归还，1：已归还）")
	private Integer status;
	
	@ApiModelProperty("用户名")
	private String username;
	
	@ApiModelProperty("设备名")
	private String equipName;
	
	@ApiModelProperty("用户签字")
	private byte[] userSign;
	
	@ApiModelProperty("借出经办人")
	private String lendHuman;
	
	@ApiModelProperty("借出经办人签字")
	private byte[] leHumanSign;
	
	
	@ApiModelProperty("老师姓名")
	@TableField("teacher_name")
	private String tecName;
	
	@ApiModelProperty("老师签字")
	private byte[] tecSign;
	
	@ApiModelProperty("返还经办人")
	private String reHuman;
	
	@ApiModelProperty("返还经办人签字")
	private byte[] reHumanSign;
	
	
	@ApiModelProperty("返还人")
	private String reUsername;
	
	@ApiModelProperty("返还人签字")
	private byte[] reUserSign;
	
	@ApiModelProperty("借出时间")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date lendTime;
	
	@ApiModelProperty("返还时间")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date returnTime;
	
	@ApiModelProperty("借用时长")
	private Integer borrowTime;
	
	@ApiModelProperty("归还倒计时")
	private Integer returnCount;
	
}