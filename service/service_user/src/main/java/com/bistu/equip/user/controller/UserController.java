package com.bistu.equip.user.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bistu.equip.common.result.Result;

import com.bistu.equip.model.user.UserInfo;
import com.bistu.equip.user.service.UserInfoService;
import com.bistu.equip.vo.user.UserInfoQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 这个是对应后台管理系统的接口
 * @author Dx666
 * @Description
 * @Date 2021/7/31 - 15:12
 */
@Api
@ResponseBody
@RestController
@RequestMapping("admin/user/")
public class UserController {
	
	// 自动注入
	@Autowired
	private UserInfoService userInfoService;
	
	@ApiOperation("获取用户列表接口")
	@GetMapping("{page}/{limit}")
	public Result getUserPage(@PathVariable("page") Long page,
	                           @PathVariable("limit") Long limit,
	                           UserInfoQueryVo userInfoQueryVo) {
		
		Page<UserInfo> pageParam = new Page<>(page, limit);
		IPage<UserInfo> pageModel = userInfoService.selectPage(pageParam, userInfoQueryVo);
		return Result.ok(pageModel);
	}
	
	@ApiOperation("删除用户")
	@DeleteMapping("{id}")
	public Result deleteById(@PathVariable Integer id) {
		boolean flag = userInfoService.removeById(id);
		if(flag) {
			return Result.ok();
		} else {
			return Result.fail();
		}
	}
	
	@ApiOperation("批量删除用户")
	@DeleteMapping("batchRemove/{idList}")
	public Result batchRemove(@PathVariable List<Integer> idList) {
		boolean flag = userInfoService.removeByIds(idList);
		if(flag) {
			return Result.ok();
		} else {
			return Result.fail();
		}
	}
	
	/**
	 * 设置用户状态接口
	 * @param id
	 * @param status
	 * @return
	 */
	@ApiOperation("修改用户状态")
	@PutMapping("lockUser/{id}/{status}")
	public Result lockUser(@PathVariable Integer id,
	                       @PathVariable Integer status) {
		// 先查询
		UserInfo user = userInfoService.getById(id);
		// 设置状态
		user.setStatus(status);
		// 修改表
		userInfoService.updateById(user);
		return Result.ok();
	}
	
}
