package com.bistu.equip.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;


import com.bistu.equip.model.user.UserInfo;
import com.bistu.equip.vo.auth.UserAuthVo;
import com.bistu.equip.vo.user.UserInfoQueryVo;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Dx666
 * @Description
 * @Date 2021/7/31 - 14:37
 */
public interface UserInfoService  extends IService<UserInfo> {
	
	
	// 分页查询用户
	IPage<UserInfo> selectPage(Page<UserInfo> pageParam, UserInfoQueryVo userInfoQueryVo);
	
	// 通过小程序openid查询用户信息
	UserInfo selectWxInfoOpenIdApplet(String openIdApplet);
	
	// 用户认证接口
	void userAuth(Long userId, UserAuthVo userAuthVo);
	
	// 批量导出用户信息
	void exportUserData(HttpServletResponse response);
	
	// 通过网站应用openid查询用户信息
	UserInfo selectWxInfoOpenIdApp(String openIdApp);
	
	
	// 通过unionId查询用户信息
	UserInfo selectWxInfoUnionId(String unionId);
}
