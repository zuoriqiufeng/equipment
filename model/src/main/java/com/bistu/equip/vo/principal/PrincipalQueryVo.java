package com.bistu.equip.vo.principal;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Dx666
 * @Description
 * @Date 2021/8/8 - 17:04
 */
@Data
public class PrincipalQueryVo {
	@ApiModelProperty("用户id")
	private Long uid;
	
	@ApiModelProperty("用户名")
	private String userName;
	
	@ApiModelProperty("借出经办人")
	private String lendHuman;
	
	@ApiModelProperty("返还经办人")
	private String reHuman;
	
	@ApiModelProperty("设备名")
	private String equipName;
	
	@ApiModelProperty("借用时长")
	private Integer borrowTime;
	
	@ApiModelProperty("是否归还（0：未归还，1：已归还）")
	private Integer status;
}
