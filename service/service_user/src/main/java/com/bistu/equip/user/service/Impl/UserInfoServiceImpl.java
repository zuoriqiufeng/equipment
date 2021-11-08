package com.bistu.equip.user.service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.bistu.equip.model.user.UserInfo;
import com.bistu.equip.user.mapper.UserInfoMapper;
import com.bistu.equip.user.service.UserInfoService;
import com.bistu.equip.vo.auth.UserAuthVo;
import com.bistu.equip.vo.user.UserInfoQueryVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author Dx666
 * @Description
 * @Date 2021/7/31 - 14:41
 */
@Slf4j
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {
	
	
	@Override
	public IPage<UserInfo> selectPage(Page<UserInfo> pageParam, UserInfoQueryVo userInfoQueryVo) {
	
		// 获取用户查询信息
		Integer accountType = userInfoQueryVo.getAccountType();
		String college = userInfoQueryVo.getCollege();
		String dept = userInfoQueryVo.getDept();
		Integer identity = userInfoQueryVo.getIdentity();
		String no = userInfoQueryVo.getNo();
		Integer status = userInfoQueryVo.getStatus();
		Integer authStatus = userInfoQueryVo.getAuthStatus();
		Integer sex = userInfoQueryVo.getSex();
		String name = userInfoQueryVo.getName();
		// 设置查询条件
		QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
		if(!StringUtils.isEmpty(accountType)) {
			wrapper.eq("account_type", accountType);
		}
		
		if(!StringUtils.isEmpty(college)) {
			wrapper.eq("college", college);
		}
		
		if(!StringUtils.isEmpty(dept)) {
			wrapper.eq("dept", dept);
		}
		
		if(!StringUtils.isEmpty(identity)) {
			wrapper.eq("identity", identity);
		}
		
		if(!StringUtils.isEmpty(no)) {
			wrapper.eq("stu_or_tec_id", no);
		}
		
		if(!StringUtils.isEmpty(status)) {
			wrapper.eq("status", status);
		}
		
		if(!StringUtils.isEmpty(authStatus)) {
			wrapper.eq("auth_status", authStatus);
		}
		if(!StringUtils.isEmpty(sex)) {
			wrapper.eq("sex", sex);
		}
		if(!StringUtils.isEmpty(name)) {
			wrapper.like("name", name);
		}
		
		// 查询数据
		Page<UserInfo> userInfoPage = baseMapper.selectPage(pageParam, wrapper);
		// 调用方法封装信息
		userInfoPage.getRecords().forEach(this::packageUserInfo);
		return userInfoPage;
	}
	

	/**
	 * 通过小程序openid查询用户信息
	 * @param openidApplet
	 * @return
	 */
	@Override
	public UserInfo selectWxInfoOpenIdApplet(String openidApplet) {
		QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
		wrapper.eq("open_id_applet", openidApplet);
		return baseMapper.selectOne(wrapper);
	}
	
	@Override
	public void userAuth(Long userId, UserAuthVo userAuthVo) {
		UserInfo userInfo = baseMapper.selectById(userId);
		// 用户认证状态
		userInfo.setAuthStatus(1);
//		userInfo.setCollege(userAuthVo.getCollege());
//		userInfo.setNo(userAuthVo.getNo());
//		userInfo.setPhone(userAuthVo.getPhone());
//		userInfo.setSex(userAuthVo.getSex());
//		userInfo.setName(userAuthVo.getName());
//		userInfo.setIdentity(userAuthVo.getIdentity());
//		userInfo.setSchoolClass(userAuthVo.getSchoolClass());
//		userInfo.setGrade(userAuthVo.getGrade());
//		userInfo.setMajor(userAuthVo.getMajor());
//		userInfo.setDept(userAuthVo.getDept());
		BeanUtils.copyProperties(userAuthVo, userInfo);
		System.out.println(userInfo);
		baseMapper.updateById(userInfo);
	}
	
	/**
	 * 批量导出用户信息
	 * @param response
	 */
	@Override
	public void exportUserData(HttpServletResponse response) {
	
	}
	
	
	/**
	 * 处理一下用户信息
	 * 即用户的状态 是否被禁用
	 * @param userInfo
	 */
	private void packageUserInfo(UserInfo userInfo) {
		log.info("在查询时,包装用户信息.....");
		// 处理用户状态编码
		String authStatus;
		String sexString = "";
		if(userInfo.getAuthStatus() == 0) {
			authStatus =  "未认证";
		} else {
			sexString = userInfo.getSex() == 0 ? "女" : "男";
			authStatus =  "已认证";
		}
		String accountType = userInfo.getAccountType() == 0 ? "普通用户" : "管理员";
		String identityString = userInfo.getIdentity() == 0 ? "学生" : "教职人员";
		String statusString = userInfo.getStatus() == 0 ? "禁用" : "正常";
		userInfo.getParam().put("identityString", identityString);
		userInfo.getParam().put("accountType", accountType);
		userInfo.getParam().put("statusString", statusString);
		userInfo.getParam().put("authStatus", authStatus);
		userInfo.getParam().put("sexString", sexString);
	}
}
