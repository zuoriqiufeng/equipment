package com.bistu.equip.princi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bistu.equip.model.principal.PrincipalInfoFront;
import com.bistu.equip.model.principal.ReturnRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author Dx666
 * @Description
 * @Date 2021/11/6 - 10:23
 */
@Mapper
public interface ReturnRecordMapper extends BaseMapper<ReturnRecord> {
	/**
	 * 通过principalId查询记录
	 * @param principalId
	 * @return
	 */
	@Select("select * from return_records where pri_id = #{principalId}")
	ReturnRecord getByPrincipalId(@Param("principalId") Long principalId);
}
