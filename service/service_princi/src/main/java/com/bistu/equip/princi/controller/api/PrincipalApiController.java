package com.bistu.equip.princi.controller.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bistu.equip.common.result.Result;
import com.bistu.equip.model.principal.PrincipalInfo;
import com.bistu.equip.princi.service.PrincipalService;
import com.bistu.equip.vo.principal.PrincipalQueryVo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * 用户端调用接口
 * @author Dx666
 * @Description
 * @Date 2021/8/8 - 19:54
 */

@Api
@RestController
@RequestMapping("api/princi/")
public class PrincipalApiController {
	@Autowired
	private PrincipalService principalService;
	
	
	/**
	 * 用户端查看历史记录
	 * @param id
	 * @return
	 */
	@GetMapping("records/{id}")
	public Result getRecords(@PathVariable Integer id) {
		Page<PrincipalInfo> page = new Page<>(0, 30);
		PrincipalQueryVo principalQueryVo = new PrincipalQueryVo();
		principalQueryVo.setUId(id);
		// 调用方法查询
		IPage<PrincipalInfo> resultPage = principalService.selectPage(page, principalQueryVo);
		return Result.ok(resultPage);
	}
}
