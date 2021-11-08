package com.bistu.equip.equipment.controller.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bistu.equip.common.result.Result;
import com.bistu.equip.equipment.service.EquipmentService;
import com.bistu.equip.model.equipment.Equipment;
import com.bistu.equip.vo.equip.EquipmentQueryVo;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Dx666
 * @Description
 * @Date 2021/10/2 - 14:22
 */
@Slf4j
@RestController
@ResponseBody
@RequestMapping("api/equip")
public class EquipmentApiController {
	
	@Autowired
	private EquipmentService equipmentService;
	
	@ApiOperation(value = "查询设备列表")
	@GetMapping("list/{page}/{limit}")
	public Result getEquipPage(@PathVariable("page") Long page,
	                           @PathVariable("limit") Long limit,
	                           EquipmentQueryVo equipmentQueryVo) {
		// 前端只需要查询出空闲的设备即可
		log.info("前台查询设备列表,只查询空闲设备....");
		equipmentQueryVo.setStatus(0);
		Page<Equipment> pageParam = new Page<>(page, limit);
		IPage<Equipment> pageModel = equipmentService.selectPage(pageParam, equipmentQueryVo);
		return Result.ok(pageModel);
	}
}
