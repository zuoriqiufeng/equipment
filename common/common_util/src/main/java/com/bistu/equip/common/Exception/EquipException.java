package com.bistu.equip.common.Exception;

import com.bistu.equip.common.result.ResultCodeEnum;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Dx666
 * @Description
 * @Date 2021/8/22 - 17:05
 */
public class EquipException extends  Exception{
	@ApiModelProperty(value = "异常状态码")
	private Integer code;
	
	
	/**
	 * 通过状态码和错误消息创建异常对象
	 * @param message
	 * @param code
	 */
	public EquipException(String message, Integer code) {
		super(message);
		this.code = code;
	}
	
	/**
	 * 接受枚举类对象
	 * @param resultCodeEnum
	 */
	public EquipException(ResultCodeEnum resultCodeEnum) {
		super(resultCodeEnum.getMessage());
		this.code = resultCodeEnum.getCode();
	}
	
	@Override
	public String toString() {
		return "EquipException{" +
				"code=" + code +
				", message=" + this.getMessage() +
				'}';
	}
}
