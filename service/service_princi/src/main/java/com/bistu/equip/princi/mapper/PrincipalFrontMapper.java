package com.bistu.equip.princi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bistu.equip.model.principal.PrincipalInfoFront;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author Dx666
 * @Description
 * @Date 2021/11/6 - 13:42
 */
@Mapper
public interface PrincipalFrontMapper extends BaseMapper<PrincipalInfoFront> {
	
	/**
	 * 通过principalId查询记录
	 * @param principalId
	 * @return
	 */
	@Select("select * from front_principal where pri_id = #{principalId}")
	PrincipalInfoFront getByPrincipalId(@Param("principalId") Long principalId);
}
