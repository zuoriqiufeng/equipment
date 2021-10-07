package com.bistu.equip.common.utils;



import com.bistu.equip.common.helper.JwtHelper;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取用户认证信息工具类
 * @author Dx666
 * @Description
 * @Date 2021/6/26 - 15:17
 */
public class AuthContextHolder {

	// 获取当前用户的id
	public static  Long getUserId(HttpServletRequest request) {
		// 从header中获取token
		String token = request.getHeader("token");
		// jwt从token中获取userid
		Long userId = JwtHelper.getUserId(token);
		return userId;
	}
	
	// 获取当前用户的名称
	public static  String  getUserName(HttpServletRequest request) {
		// 从header中获取token
		String token = request.getHeader("token");
		// jwt从token中获取userid
		String userName = JwtHelper.getUserName(token);
		return userName;
	}
	
}
