package com.bistu.equip.model.principal;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
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
public class PrincipalInfo {
	
	@ApiModelProperty("用户编号")
	@TableField("uid")
	private Integer uId;
	
	@ApiModelProperty("设备号")
	@TableField("equip_id")
	private Integer equipId;
	
	@ApiModelProperty("用户名")
	@TableField("user_name")
	private String userName;
	
	@ApiModelProperty("设备名")
	@TableField("equip_name")
	private String equipName;
	
	@ApiModelProperty("用户签字")
	@TableField("user_sign")
	private byte[] userSign;
	
	@ApiModelProperty("借出经办人")
	@TableField("lend_human")
	private String lendHuman;
	
	@ApiModelProperty("借出经办人签字")
	@TableField("le_human_sign")
	private byte[] leHumanSign;
	
	@ApiModelProperty("老师签字")
	@TableField("teacher_sign")
	private byte[] tecSign;
	
	@ApiModelProperty("返还经办人")
	@TableField("re_human")
	private String reHuman;
	
	@ApiModelProperty("返还经办人签字")
	@TableField("re_human_sign")
	private byte[] reHumanSign;
	
	@ApiModelProperty("借出时间")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@TableField("lend_time")
	private Date lendTime;
	
	@ApiModelProperty("返还时间")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@TableField("return_time")
	private Date returnTime;
	
	@ApiModelProperty("借用时长")
	@TableField("borrow_time")
	private Integer borrowTime;
	
	@ApiModelProperty(value = "创建时间")
	@JsonFormat(pattern = "yyyy-MM-DD HH:mm:ss")
	@TableField("create_time")
	private Date createTime;
	
	@ApiModelProperty(value = "更新时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	@TableField("update_time")
	private Date updateTime;
	
	@ApiModelProperty(value = "其他参数")
	@TableField(exist = false)
	private Map<String, Object> param = new HashMap<>();

}
