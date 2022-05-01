package com.bistu.equip.user.client;




import com.bistu.equip.model.user.UserInfo;
import com.bistu.equip.common.result.Result;
import com.bistu.equip.common.utils.AuthContextHolder;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Dx666
 * @Description
 * @Date 2021/9/22 - 9:03
 */
@Repository
@FeignClient("service-user")
public interface UserFeignClient {
	/**
	 * 获取用户信息
	 * @param id
	 * @return
	 */
	@ApiOperation("根据id获取用户据信息")
	@GetMapping("admin/user/getUserInfo")
	public UserInfo getUserInfo(Long id);
	
}
