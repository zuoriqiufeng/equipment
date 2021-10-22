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
	
	// 通过openid查询用户信息
	UserInfo selectWxInfoOpenId(String openid);
	
	// 用户认证接口
	void userAuth(Long userId, UserAuthVo userAuthVo);
	
	// 批量导出用户信息
	void exportUserData(HttpServletResponse response);
}
