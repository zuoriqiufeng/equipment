package com.bistu.equip.princi.controller.api;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bistu.equip.common.result.Result;
import com.bistu.equip.common.utils.AuthContextHolder;
import com.bistu.equip.equipment.client.EquipFeignClient;
import com.bistu.equip.model.principal.PrincipalInfo;
import com.bistu.equip.princi.service.PrincipalService;
import com.bistu.equip.vo.principal.PrincipalBorrowVo;
import com.bistu.equip.vo.principal.PrincipalQueryVo;
import com.bistu.equip.vo.principal.PrincipalReturnVo;
import com.sun.org.apache.bcel.internal.generic.DDIV;
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
	
	/**
	 * 用户端查看历史记录
	 * @return
	 *
	 */
	@ApiOperation("用户查看历史记录")
	@GetMapping("auth/records/{page}/{limit}")
	public Result getRecords(@PathVariable Long page, @PathVariable Long limit, HttpServletRequest request) {
		log.info("用户历史记录查询......");
		Long id = AuthContextHolder.getUserId(request);
		Page<PrincipalInfo> pageInfo = new Page<>(page, limit);
		// 调用方法查询
		PrincipalQueryVo principalQueryVo = new PrincipalQueryVo();
		principalQueryVo.setUid(id);
		IPage<PrincipalInfo> resultPage = principalService.selectPage(pageInfo, principalQueryVo);
		return Result.ok(resultPage);
	}
	
	@ApiOperation("设备借出接口")
	@PostMapping("auth/borrow")
	public Result borrow(@RequestBody() PrincipalBorrowVo principalBorrowVo, HttpServletRequest request) {
		log.info("设备借出......");
		Long userId = AuthContextHolder.getUserId(request);
		
		principalBorrowVo.setUid(userId);
		principalService.borrow(principalBorrowVo);
		return Result.ok();
	}
	
	@ApiOperation("设备归还接口")
	@PostMapping("auth/return/{id}")
	public Result returnEquip(@PathVariable Long id,
	                          @RequestBody PrincipalReturnVo principalReturnVo) {
		log.info("设备归还");
		principalService.returnEquip(principalReturnVo, id);
		return Result.ok();
	}
	
	@ApiOperation("上传教师签名接口")
	@PostMapping("auth/imgUpload/teacher/{equipId}")
	public Result uploadImg(HttpServletRequest request,
	                        @PathVariable Long equipId,
	                        MultipartFile file) {
		Long uid = AuthContextHolder.getUserId(request);
		try {
			byte[] bytes = file.getBytes();
			principalService.uploadImgTeacher(uid, equipId, bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Result.ok();
	}
	
	// 测试接口
	@PostMapping("test")
	public Result test() {
		equipFeignClient.modifyStatus(2L, 1);
		return Result.ok();
	}
}
