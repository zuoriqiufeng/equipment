package com.bistu.equip.vo.equip;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author Dx666
 * @Description
 * @Date 2021/7/31 - 18:19
 */
@Data
@ApiModel(description = "搜索对象")
public class EquipmentQueryVo {
	
	@ApiModelProperty(value = "设备名称")
	private String  name;
	
	@ApiModelProperty(value = "类型(0: 个人设备 1: 未入库设备 2: 已入库设备)")
	private Integer type;

	@ApiModelProperty(value = "设备状态(0:空闲 1：使用中 2：已损坏 3：正在维修)")
	private Integer status;
	
}

