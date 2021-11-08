package com.bistu.equip.model.principal;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.bistu.equip.model.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Dx666
 * @Description
 * @Date 2021/11/5 - 22:29
 */
@Data
@TableName("signs")
public class Sign extends BaseEntity {
	
	@ApiModelProperty("后台记录id")
	@TableField("pri_id")
	private Long principalId;
	
	@ApiModelProperty("用户签字")
	@TableField("user_sign")
	private byte[] userSign;
	
	@ApiModelProperty("返还经办人签字")
	@TableField("re_human_sign")
	private byte[] reHumanSign;
	
	@ApiModelProperty("老师签字")
	@TableField("tec_sign")
	private byte[] tecSign;
	
	@ApiModelProperty("借出经办人签字")
	@TableField("le_human_sign")
	private byte[] leHumanSign;
	
	@ApiModelProperty("返还人签字")
	@TableField("re_user_sign")
	private byte[] reUserSign;
}
