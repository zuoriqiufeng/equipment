package com.bistu.equip.equipment.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bistu.equip.common.result.Result;
import com.bistu.equip.equipment.service.EquipmentService;
import com.bistu.equip.model.equipment.Equipment;
import com.bistu.equip.vo.equip.EquipmentQueryVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


/**
 * @author Dx666
 * @Description
 * @Date 2021/7/31 - 18:13
 */
@ResponseBody
@RestController
@RequestMapping("admin/equip")
public class EquipmentController {
	
	@Autowired
	private EquipmentService equipmentService;

	@ApiOperation(value = "查询设备列表")
	@GetMapping("list/{page}/{limit}")
	public Result getEquipPage(@PathVariable("page") Long page,
	                           @PathVariable("limit") Long limit,
	                           EquipmentQueryVo equipmentQueryVo) {
		Page<Equipment> pageParam = new Page<>(page, limit);
		IPage<Equipment> pageModel = equipmentService.selectPage(pageParam, equipmentQueryVo);
		return Result.ok(pageModel);
	}
	
	
	
	@ApiOperation(value = "保存设备信息")
	@PostMapping("save")
	public Result save(@RequestBody Equipment equipment) {
		
		
		equipment.setCreateTime(new Date());
		equipment.setUpdateTime(new Date());
		equipmentService.save(equipment);
		return Result.ok();
	}
	
	@ApiOperation("删除设备")
	@DeleteMapping("{id}")
	public Result deleteById(@PathVariable Integer id) {
		
		boolean flag = equipmentService.removeById(id);
		
		if(flag) {
			return Result.ok();
		} else {
			return Result.fail();
		}
	}
	
	@ApiOperation("批量删除设备")
	@DeleteMapping("batchRemove")
	public Result batchRemove(@RequestBody List<Integer> idList) {
		equipmentService.removeByIds(idList);
		return Result.ok();
	}
	
	@ApiOperation("修改设备状态")
	@PutMapping("modifyStatus/{id}/{status}")
	public Result modifyStatus(@PathVariable("id") Integer id,
	                           @PathVariable("status") Integer status) {
		
		Equipment equipment = equipmentService.getById(id);
		equipment.setStatus(status);
		equipmentService.updateById(equipment);
		return null;
	}
	
	@ApiOperation("获取借用详情信息")
	@PutMapping("getBorrowDetail")
	public Result getBorrowDetail() {
		return null;
	}
	
	
}
