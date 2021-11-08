package com.bistu.equip.princi.service.impl;

import com.alibaba.excel.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bistu.equip.model.principal.PrincipalInfoFront;
import com.bistu.equip.model.principal.ReturnRecord;
import com.bistu.equip.princi.mapper.PrincipalFrontMapper;
import com.bistu.equip.princi.service.PrincipalFrontService;
import com.bistu.equip.princi.service.ReturnRecordService;
import com.bistu.equip.vo.principal.PrincipalQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Dx666
 * @Description
 * @Date 2021/11/6 - 13:44
 */
@Service
public class PrincipalFrontServiceImpl extends ServiceImpl<PrincipalFrontMapper, PrincipalInfoFront> implements PrincipalFrontService {
	
	@Autowired
	private ReturnRecordService returnRecordService;
	
	@Override
	public IPage<PrincipalInfoFront> selectPage(Page<PrincipalInfoFront> pageParam, PrincipalQueryVo principalQueryVo) {
		// 获取查询参数
		String equipName = principalQueryVo.getEquipName();
		Integer borrowTime = principalQueryVo.getBorrowTime();
		Integer status = principalQueryVo.getStatus();
		String lendHuman = principalQueryVo.getLendHuman();
		String reHuman = principalQueryVo.getReHuman();
		Long uid = principalQueryVo.getUid();
		String userName = principalQueryVo.getUserName();
		QueryWrapper<PrincipalInfoFront> wrapper = new QueryWrapper<>();
		// 设置查询条件
		if(!StringUtils.isEmpty(lendHuman)) {
			wrapper.like("lend_human", lendHuman);
		}
		if(!StringUtils.isEmpty(reHuman)) {
			wrapper.like("re_human", reHuman);
		}
		
		if(!StringUtils.isEmpty(uid)) {
			wrapper.eq("uid", uid);
		}
		
		if(!StringUtils.isEmpty(userName)) {
			wrapper.like("user_name", userName);
		}
		
		if(!StringUtils.isEmpty(status)) {
			wrapper.eq("status", status);
		}
		
		if(!StringUtils.isEmpty(borrowTime)) {
			wrapper.eq("borrow_time", borrowTime);
		}
		
		if(!StringUtils.isEmpty(equipName)) {
			wrapper.like("equip_name", equipName);
		}
		Page<PrincipalInfoFront> principalFrontPage = baseMapper.selectPage(pageParam, wrapper);
		// 封装数据信息
		principalFrontPage.getRecords().forEach(this::packageInfo);
		return principalFrontPage;
	}
	
	@Override
	public PrincipalInfoFront getByPrincipalId(Long principalId) {
		PrincipalInfoFront principalInfoFront = baseMapper.getByPrincipalId(principalId);
		return principalInfoFront;
	}
	
	
	/**
	 * 封装数据信息
	 * @param principalInfoFront
	 */
	private void packageInfo(PrincipalInfoFront principalInfoFront) {
		Long id = principalInfoFront.getPrincipalId();
		ReturnRecord returnRecord = returnRecordService.getByPrincipalId(id);
		principalInfoFront.setReturnCount(returnRecord.getReturnCount());
		String returnStatus = null;
		if(principalInfoFront.getStatus() == 1) {
			returnStatus = "已归还";
		} else {
			returnStatus = "未归还";
		}
		principalInfoFront.getParam().put("returnStatus", returnStatus);
	}
}
