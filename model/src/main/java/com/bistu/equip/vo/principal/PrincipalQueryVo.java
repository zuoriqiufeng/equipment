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
	
	@ApiModelProperty("用户编号")
	private Integer uId;
	
	@ApiModelProperty("设备号")
	private Integer equipId;
	
	@ApiModelProperty("用户名")
	private String userName;
	
	@ApiModelProperty("借出经办人")
	private String lendHuman;
	
	@ApiModelProperty("返还经办人")
	private String reHuman;
}
