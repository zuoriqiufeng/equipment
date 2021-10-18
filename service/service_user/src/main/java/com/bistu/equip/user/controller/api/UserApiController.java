package com.bistu.equip.user.controller.api;

import com.bistu.equip.common.helper.JwtHelper;
import com.bistu.equip.common.result.Result;
import com.bistu.equip.common.utils.AuthContextHolder;
import com.bistu.equip.model.user.UserInfo;
import com.bistu.equip.user.service.UserInfoService;
import com.bistu.equip.vo.auth.UserAuthVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author Dx666
 * @Description
 * @Date 2021/8/8 - 16:04
 */
@RestController
@RequestMapping("api/user/")
public class UserApiController {
	
	
	@Autowired
	private UserInfoService userInfoService;
	
	
	@ApiOperation("用户认证接口")
	@PostMapping("auth/userAuth")
	public Result userAuth(@RequestBody UserAuthVo userAuthVo,HttpServletRequest request) {
		System.out.println(userAuthVo);
		Long userId = AuthContextHolder.getUserId(request);
		userInfoService.userAuth(userId, userAuthVo);
		return Result.ok();
	}
	
	
	@ApiOperation("用户登录接口")
	@PostMapping("login")
	public Result login(@RequestParam("openid") String openid) {
		// 通过openid查询
		String name;
		UserInfo userInfo = userInfoService.selectWxInfoOpenId(openid);
		HashMap<String, String> result = new HashMap();
		if(userInfo == null) {
			userInfo = new UserInfo();
			userInfo.setOpenId(openid);
			userInfo.setLastLoginTime(new Date());
			userInfoService.save(userInfo);
			result.put("authStatus", "0");
			name = openid;
		} else {
			result.put("authStatus", userInfo.getAuthStatus().toString());
			name = userInfo.getName();
		}
		String token = JwtHelper.createToken(userInfo.getId(), name);
		result.put("token", token);
		return Result.ok(result);
	}
	
	
	@ApiOperation("根据id获取用户据信息")
	@GetMapping("auth/getUserInfo")
	public Result getInfo(HttpServletRequest request) {
		Long userId = AuthContextHolder.getUserId(request);
		UserInfo userInfo = userInfoService.getById(userId);
		return Result.ok(userInfo);
	}
	
}
