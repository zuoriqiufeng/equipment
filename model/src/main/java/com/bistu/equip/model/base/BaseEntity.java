package com.bistu.equip.model.base;

import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Dx666
 * @Description
 * @Date 2021/7/30 - 20:59
 */
@Data
public class BaseEntity implements Serializable {
	
	@ApiModelProperty(value = "id")
	@TableId(type = IdType.AUTO)
	private Long id;
	
	@ApiModelProperty(value = "创建时间")
	@DateTimeFormat("yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@TableField(value = "create_time", fill = FieldFill.INSERT)
	private Date createTime;
	
	@ApiModelProperty(value = "更新时间")
	@DateTimeFormat("yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-DD HH:mm:ss")
	@TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
	private Date updateTime;

	@ApiModelProperty(value = "其他参数")
	@TableField(exist = false)
	private Map<String, Object> param = new HashMap<>();
	
}
