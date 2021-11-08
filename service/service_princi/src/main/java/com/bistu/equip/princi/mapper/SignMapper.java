package com.bistu.equip.princi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bistu.equip.model.principal.PrincipalInfoFront;
import com.bistu.equip.model.principal.Sign;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author Dx666
 * @Description
 * @Date 2021/11/6 - 10:26
 */
@Mapper
public interface SignMapper extends BaseMapper<Sign> {
	
	/**
	 * 通过principalId查询记录
	 * @param principalId
	 * @return
	 */
	@Select("select * from signs where pri_id = #{principalId}")
	Sign getByPrincipalId(@Param("principalId") Long principalId);
}
