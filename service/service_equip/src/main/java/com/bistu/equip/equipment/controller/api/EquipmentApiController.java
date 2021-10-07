package com.bistu.equip.equipment.controller.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bistu.equip.common.result.Result;
import com.bistu.equip.equipment.service.EquipmentService;
import com.bistu.equip.model.equipment.Equipment;
import com.bistu.equip.vo.equip.EquipmentQueryVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Dx666
 * @Description
 * @Date 2021/10/2 - 14:22
 */
@RestController("api/equip")
public class EquipmentApiController {
	
	@Autowired
	private EquipmentService equipmentService;
	
	@ApiOperation(value = "查询设备列表")
	@GetMapping("list/{page}/{limit}")
	public Result getEquipPage(@PathVariable("page") Long page,
	                           @PathVariable("limit") Long limit,
	                           EquipmentQueryVo equipmentQueryVo) {
		// 前端只需要查询出空闲的设备即可
		equipmentQueryVo.setStatus(0);
		Page<Equipment> pageParam = new Page<>(page, limit);
		IPage<Equipment> pageModel = equipmentService.selectPage(pageParam, equipmentQueryVo);
		return Result.ok(pageModel);
	}
}
