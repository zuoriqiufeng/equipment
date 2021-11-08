package com.bistu.equip.model.principal;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.bistu.equip.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author Dx666
 * @Description
 * @Date 2021/11/5 - 21:53
 */
@Data
@TableName("return_records")
public class ReturnRecord  extends BaseEntity {
	
	@TableField("pri_id")
	private Long principalId;
	
	@TableField("return_count")
	private Integer returnCount;
	
	@TableField("status")
	private Integer status;
}
