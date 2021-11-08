package com.bistu.equip.princi.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bistu.equip.model.principal.PrincipalInfo;
import com.bistu.equip.model.principal.PrincipalInfoFront;
import com.bistu.equip.vo.principal.PrincipalQueryVo;
import org.apache.ibatis.annotations.Param;

/**
 * @author Dx666
 * @Description
 * @Date 2021/11/6 - 13:43
 */
public interface PrincipalFrontService extends IService<PrincipalInfoFront> {
	
	/**
	 * 分页获取借用情况列表
	 * @param pageParam
	 * @param principalQueryVo
	 * @return
	 */
	IPage<PrincipalInfoFront> selectPage(Page<PrincipalInfoFront> pageParam, PrincipalQueryVo principalQueryVo);
	
	/**
	 * 通过principalI查询一条记录
	 * @param principalId
	 * @return
	 */
	PrincipalInfoFront getByPrincipalId(Long principalId);
}
