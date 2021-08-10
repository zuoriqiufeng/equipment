package com.bistu.equip.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;


import com.bistu.equip.model.user.UserInfo;
import com.bistu.equip.vo.user.UserInfoQueryVo;

/**
 * @author Dx666
 * @Description
 * @Date 2021/7/31 - 14:37
 */
public interface UserInfoService  extends IService<UserInfo> {
	
	
	// 分页查询用户
	IPage<UserInfo> selectPage(Page<UserInfo> pageParam, UserInfoQueryVo userInfoQueryVo);
}
