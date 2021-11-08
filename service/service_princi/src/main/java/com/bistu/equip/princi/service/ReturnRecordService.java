package com.bistu.equip.princi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bistu.equip.model.principal.PrincipalInfoFront;
import com.bistu.equip.model.principal.ReturnRecord;

import java.util.List;

/**
 * @author Dx666
 * @Description
 * @Date 2021/11/6 - 10:24
 */
public interface ReturnRecordService extends IService<ReturnRecord> {
	/**
	 * 通过principalI查询一条记录
	 * @param principalId
	 * @return
	 */
	ReturnRecord getByPrincipalId(Long principalId);
	
	/**
	 * 获取离归还期限还剩3天，却还没有归还的记录
	 * @return
	 */
	List<ReturnRecord> getToReturned();
}
