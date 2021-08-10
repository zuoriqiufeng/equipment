package com.bistu.equip.princi.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bistu.equip.model.principal.PrincipalInfo;
import com.bistu.equip.vo.principal.PrincipalQueryVo;

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
}
