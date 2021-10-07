package com.bistu.equip.vo.principal;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author Dx666
 * @Description
 * @Date 2021/10/4 - 19:22
 */
@Data
public class PrincipalReturnVo {
	
	@ApiModelProperty("用户id")
	private Long uid;
	
	@ApiModelProperty("设备id")
	private Long equipId;
	
	@ApiModelProperty("返还经办人")
	private String reHuman;
	@ApiModelProperty("返还经办人签字")
	private String reHumanSign;
	
	@ApiModelProperty("返还时间")
	private Date returnTime;
}
