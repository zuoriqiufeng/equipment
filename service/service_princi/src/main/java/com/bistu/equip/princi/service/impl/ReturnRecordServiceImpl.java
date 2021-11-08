package com.bistu.equip.princi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bistu.equip.model.principal.PrincipalInfoFront;
import com.bistu.equip.model.principal.ReturnRecord;
import com.bistu.equip.princi.mapper.ReturnRecordMapper;
import com.bistu.equip.princi.service.ReturnRecordService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Dx666
 * @Description
 * @Date 2021/11/6 - 10:25
 */
@Service
public class ReturnRecordServiceImpl  extends ServiceImpl<ReturnRecordMapper, ReturnRecord> implements ReturnRecordService  {
	
	/**
	 * 通过principalId查询记录
	 * @param principalId
	 * @return
	 */
	@Override
	public ReturnRecord getByPrincipalId(Long principalId) {
		ReturnRecord returnRecord = baseMapper.getByPrincipalId(principalId);
		return returnRecord;
	}
	
	/**
	 * 获取还剩三天却还没有归还的记录
	 * @return
	 */
	@Override
	public List<ReturnRecord> getToReturned() {
		QueryWrapper<ReturnRecord> wrapper = new QueryWrapper<>();
		wrapper.le("return_count", 3);
		wrapper.eq("status", 1);
		List<ReturnRecord> returnRecords = baseMapper.selectList(wrapper);
		return returnRecords;
	}
}
