package com.bistu.equip.princi.controller.api;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bistu.equip.common.result.Result;
import com.bistu.equip.common.result.ResultCodeEnum;
import com.bistu.equip.common.utils.AuthContextHolder;
import com.bistu.equip.equipment.client.EquipFeignClient;
import com.bistu.equip.model.principal.PrincipalInfo;
import com.bistu.equip.model.principal.PrincipalInfoFront;
import com.bistu.equip.princi.service.PrincipalFrontService;
import com.bistu.equip.princi.service.PrincipalService;
import com.bistu.equip.vo.principal.PrincipalBorrowVo;
import com.bistu.equip.vo.principal.PrincipalQueryVo;
import com.bistu.equip.vo.principal.PrincipalReturnVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


/**
 * 设备的借用接口
 * 用户端调用接口
 * @author Dx666
 * @Description
 * @Date 2021/8/8 - 19:54
 */
@Slf4j
@Api
@RestController
@RequestMapping("api/princi/")
public class PrincipalApiController {
	@Autowired
	private PrincipalService principalService;
	@Autowired
	private EquipFeignClient equipFeignClient;
	
	@Autowired
	private PrincipalFrontService principalFrontService;
	
	/**
	 * 用户端查看历史记录
	 * @return
	 *
	 */
	@ApiOperation("用户查看历史记录")
	@GetMapping("auth/records/{page}/{limit}")
	public Result getRecords(@PathVariable Long page,
	                         @PathVariable Long limit,
	                         PrincipalQueryVo principalQueryVo,
	                         HttpServletRequest request) {
		log.info("用户历史记录查询......");
		Long id = AuthContextHolder.getUserId(request);
		Page<PrincipalInfoFront> pageInfo = new Page<>(page, limit);
		// 调用方法查询
		principalQueryVo.setUid(id);
		IPage<PrincipalInfoFront> resultPage = principalFrontService.selectPage(pageInfo, principalQueryVo);
		return Result.ok(resultPage);
	}
	
	@ApiOperation("设备借出接口")
	@PostMapping("auth/borrow")
	public Result borrow(@RequestBody() PrincipalBorrowVo principalBorrowVo, HttpServletRequest request) {
		log.info("设备借出......");
		Long userId = AuthContextHolder.getUserId(request);
		// 验证表单是否重复提交
		QueryWrapper<PrincipalInfo> wrapper = new QueryWrapper<>();
		wrapper.eq("uid", userId);
		wrapper.eq("equip_id", principalBorrowVo.getEquipId());
		wrapper.eq("status", 0);
		PrincipalInfo result = principalService.getOne(wrapper);
		if(result != null) {
			return Result.fail(ResultCodeEnum.FORM_REPEAT_SUBMIT);
		}
		principalBorrowVo.setUid(userId);
		principalService.borrow(principalBorrowVo) ;
		return Result.ok();
	}
	
	@ApiOperation("设备归还接x口")
	@PostMapping("auth/return/{id}")
	public Result returnEquip(@PathVariable Long id,
	                          @RequestBody PrincipalReturnVo principalReturnVo) {
		
		System.out.println(id);
		log.info("设备归还");
		principalService.returnEquip(principalReturnVo, id);
		return Result.ok();
	}

	
	/**
	 * 测试接口
 	 * @return
	 */
	@PostMapping("test")
	public Result test() {
		equipFeignClient.modifyStatus(2L, 1);
		return Result.ok();
	}
}
