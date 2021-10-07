package com.bistu.equip.princi.client;

import com.bistu.equip.common.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Dx666
 * @Description
 * @Date 2021/9/22 - 9:03
 */
@Repository
@FeignClient("service-princi")
public interface PrinciFeignClient {
	
	/**
	 * 查询全部的借用信息
	 * @return
	 */
	@GetMapping("all")
	public Result getAllData();
}
