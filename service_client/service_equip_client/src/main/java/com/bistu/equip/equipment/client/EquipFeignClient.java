package com.bistu.equip.equipment.client;

import com.bistu.equip.common.result.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

/**
 *
 * 模块之间的调用接口，相当于两个工程之间的相互调用
 * @author Dx666
 * @Description
 * @Date 2021/9/21 - 13:30
 */
@Repository
@FeignClient("service-equip")
public interface EquipFeignClient {
	
	/**
	 * 修改设备状态方法
	 * @param id
	 * @param status
	 * @return
	 */
	@ApiOperation("修改设备状态")
	@PutMapping("modifyStatus/{id}/{status}")
	Result modifyStatus(@PathVariable("id") Long id,
	                           @PathVariable("status") Integer status);
}
