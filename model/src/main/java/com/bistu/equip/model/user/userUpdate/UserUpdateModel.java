package com.bistu.equip.model.user.userUpdate;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Dx666
 * @Description
 * @Date 2021/9/29 - 16:04
 */
@Data
public class UserUpdateModel {
	
	@ApiModelProperty("用户id")
	private Long id;
	private Integer identity;
	private Integer accountType;
	private String name;
}
