package com.bistu.equip.vo.principal;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author Dx666
 * @Description
 * @Date 2021/10/4 - 19:21
 */
@Data
public class PrincipalBorrowVo {
	
	@ApiModelProperty("用户id")
	private Long uid;
	
	@ApiModelProperty("设备id")
	private Long equipId;
	
	@ApiModelProperty("设备名")
	private String equipName;
	
	@ApiModelProperty("用户名")
	private String username;
	
	@ApiModelProperty("用户签字")
	private String userSign;
	
	@ApiModelProperty("借出经办人")
	private String lendHuman;
	
	@ApiModelProperty("借出经办人签字")
	private String leHumanSign;
	
	@ApiModelProperty("老师姓名")
	private String tecName;
	
	@ApiModelProperty("老师签字")
	private String tecSign;
	
	@ApiModelProperty("借用时长")
	private Integer borrowTime;
	
}
