package com.bistu.equip.user.service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.bistu.equip.model.user.UserInfo;
import com.bistu.equip.user.mapper.UserInfoMapper;
import com.bistu.equip.user.service.UserInfoService;
import com.bistu.equip.vo.user.UserInfoQueryVo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author Dx666
 * @Description
 * @Date 2021/7/31 - 14:41
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {
	
	
	@Override
	public IPage<UserInfo> selectPage(Page<UserInfo> pageParam, UserInfoQueryVo userInfoQueryVo) {
	
		// 获取用户查询信息
		Integer accountType = userInfoQueryVo.getAccountType();
		String college = userInfoQueryVo.getCollege();
		String dept = userInfoQueryVo.getDept();
		Integer identity = userInfoQueryVo.getIdentity();
		String major = userInfoQueryVo.getMajor();
		String no = userInfoQueryVo.getNo();
		Integer status = userInfoQueryVo.getStatus();
		
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
		
		if(!StringUtils.isEmpty(major)) {
			wrapper.eq("major", major);
		}
		
		if(!StringUtils.isEmpty(no)) {
			wrapper.eq("no", no);
		}
		
		if(!StringUtils.isEmpty(status)) {
			wrapper.eq("status", status);
		}

		// 查询数据
		Page<UserInfo> userInfoPage = baseMapper.selectPage(pageParam, wrapper);
		// 调用方法封装信息
		userInfoPage.getRecords().forEach(this::packageUserInfo);
		return userInfoPage;
	}
	
	
	/**
	 * 处理一下用户信息
	 * 即用户的状态 是否被禁用
	 * @param userInfo
	 */
	private void packageUserInfo(UserInfo userInfo) {
		
		// 处理用户状态编码
		String statusString = userInfo.getStatus() == 0 ? "禁用" : "正常";
		String accountType = userInfo.getAccountType() == 0 ? "普通用户" : "管理员";
		String identityString = userInfo.getIdentity() == 0 ? "学生" : "老师";
		
		userInfo.getParam().put("identityString", identityString);
		userInfo.getParam().put("accountType", accountType);
		userInfo.getParam().put("statusString", statusString);
	}
}
