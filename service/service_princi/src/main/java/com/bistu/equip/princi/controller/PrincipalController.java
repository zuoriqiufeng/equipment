package com.bistu.equip.princi.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bistu.equip.common.result.Result;
import com.bistu.equip.model.principal.PrincipalInfo;
import com.bistu.equip.princi.service.PrincipalService;
import com.bistu.equip.vo.principal.PrincipalQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * @author Dx666
 * @Description
 * @Date 2021/8/8 - 16:47
 */
@Api
@ResponseBody
@RestController
@RequestMapping("admin/princi/")
public class PrincipalController {
	
	@Autowired
	public PrincipalService principalService;
	
	
	@ApiOperation("获取借用情况列表")
	@PostMapping("list/{page}/{limit}")
	public Result getPagePrincipal(@PathVariable("page") Long page,
	                               @PathVariable("limit") Long limit,
	                               PrincipalQueryVo principalQueryVo) {
		Page<PrincipalInfo> pageParam = new Page<>(page, limit);
		IPage<PrincipalInfo> pageModel = principalService.selectPage(pageParam, principalQueryVo);
		
		return Result.ok(pageModel);
	}
	
}
