package com.bistu.equip.princi.service.impl;
import com.alibaba.excel.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bistu.equip.model.principal.PrincipalInfo;
import com.bistu.equip.princi.mapper.PrincipalMapper;
import com.bistu.equip.princi.service.PrincipalService;
import com.bistu.equip.vo.principal.PrincipalQueryVo;
import org.springframework.stereotype.Service;

/**
 * @author Dx666
 * @Description
 * @Date 2021/8/8 - 16:50
 */
@Service
public class PrincipalServiceImpl extends ServiceImpl<PrincipalMapper, PrincipalInfo> implements PrincipalService {
	
	/**
	 * 分页查询记录
	 * @param pageParam
	 * @param principalQueryVo
	 * @return
	 */
	@Override
	public IPage<PrincipalInfo> selectPage(Page<PrincipalInfo> pageParam, PrincipalQueryVo principalQueryVo) {
		// 获取查询参数
		String lendHuman = principalQueryVo.getLendHuman();
		Integer equipId = principalQueryVo.getEquipId();
		String reHuman = principalQueryVo.getReHuman();
		Integer uId = principalQueryVo.getUId();
		String userName = principalQueryVo.getUserName();
		
		QueryWrapper<PrincipalInfo> wrapper = new QueryWrapper<>();
		
		// 设置查询条件
		if(!StringUtils.isEmpty(lendHuman)) {
			wrapper.eq("lend_human", lendHuman);
		}
		
		if(!StringUtils.isEmpty(equipId)) {
			wrapper.eq("equip_id", equipId);
		}
		
		if(!StringUtils.isEmpty(reHuman)) {
			wrapper.eq("re_human", reHuman);
		}
		
		if(!StringUtils.isEmpty(lendHuman)) {
			wrapper.eq("lend_human", lendHuman);
		}
		
		if(!StringUtils.isEmpty(uId)) {
			wrapper.eq("uid", uId);
		}
		
		if(!StringUtils.isEmpty(userName)) {
			wrapper.eq("user_name", userName);
		}
		
		return baseMapper.selectPage(pageParam, wrapper);
	}
	
}
