package com.bistu.equip.princi.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bistu.equip.model.principal.PrincipalInfo;
import com.bistu.equip.vo.principal.PrincipalBorrowVo;
import com.bistu.equip.vo.principal.PrincipalQueryVo;
import com.bistu.equip.vo.principal.PrincipalReturnVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dx666
 * @Description
 * @Date 2021/8/8 - 16:50
 */
public interface PrincipalService extends IService<PrincipalInfo> {
	
	/**
	 * 分页获取借用情况列表
	 * @param pageParam
	 * @param principalQueryVo
	 * @return
	 */
	IPage<PrincipalInfo> selectPage(Page<PrincipalInfo> pageParam, PrincipalQueryVo principalQueryVo);
	
	/**
	 * 上传教师签名接口
	 * @param uid
	 * @param equipId
	 * @param bytes
	 */
	void uploadImgTeacher(Long uid, Long equipId, byte[] bytes);
	
	/**
	 * 获取全部数据
	 * @return
	 */
	List<PrincipalInfo>getAllInfo();
	
	/**
	 * 归还设备接口
	 * @param principalInfo
	 */
	void updatePrincipal(PrincipalInfo principalInfo);
	
	/**
	 * 借用设备方法
	 * @param principalBorrowVo
	 */
	void borrow(PrincipalBorrowVo principalBorrowVo);
	
	/**
	 * 归还设备方法
	 * @param principalReturnVo
	 */
	void returnEquip(PrincipalReturnVo principalReturnVo, Long id);
}
