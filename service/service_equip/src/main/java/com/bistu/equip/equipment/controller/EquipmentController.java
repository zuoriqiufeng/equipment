package com.bistu.equip.equipment.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bistu.equip.common.Exception.EquipException;
import com.bistu.equip.common.result.Result;
import com.bistu.equip.common.result.ResultCodeEnum;
import com.bistu.equip.equipment.service.EquipmentService;
import com.bistu.equip.model.equipment.Equipment;
import com.bistu.equip.vo.equip.EquipmentQueryVo;
import feign.Body;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.util.calendar.LocalGregorianCalendar;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * @author Dx666
 * @Description
 * @Date 2021/7/31 - 18:13
 */
@Slf4j
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
		log.info("开始分页查询....");
		Page<Equipment> pageParam = new Page<>(page, limit);
		IPage<Equipment> pageModel = equipmentService.selectPage(pageParam, equipmentQueryVo);
		log.info("分页查询结束返回结果...");
		return Result.ok(pageModel);
	}
	
	@ApiOperation(value = "保存设备信息")
	@PostMapping("save")
	public Result save(@RequestBody Equipment equipment) {
		log.info("开始添加设备......");
		equipment.setDeviceId(this.getDeviceId());
		boolean save = equipmentService.save(equipment);
		if(save) {
			log.info("设备添加成功......");
			return Result.ok();
		} else {
			log.error("设备添加失败......");
			return Result.fail(ResultCodeEnum.DATA_ERROR);
		}
	}
	
	@ApiOperation("删除设备")
	@DeleteMapping("{id}")
	public Result deleteById(@PathVariable Integer id) {
		log.info("删除设备......");
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
	public Result modifyStatus(@PathVariable("id") Long id,
	                           @PathVariable("status") Integer status) {
		log.info("修改设备状态");
		Equipment equipment = equipmentService.getById(id);
		equipment.setStatus(status);
		equipmentService.updateById(equipment);
		return null;
	}
	
	
	
	@ApiOperation("批量导入设备信息")
	@PostMapping("importData")
	public Result importEquip(MultipartFile file){
		log.info("导入设备数据");
		equipmentService.importEquipData(file);
		return Result.ok();
	}
	
	@ApiOperation("批量导出设备信息")
	@GetMapping("exportData")
	public void exportEquip(HttpServletResponse response){
		log.info("导出设备数据......");
		try {
			equipmentService.exportEquipData(response);
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@ApiOperation("更新设备信息")
	@PostMapping("update")
	public Result updateInfo(@RequestBody Equipment equipment) {
		// 调用方法更新设备信息
		log.info("更新设备信息......");
		equipmentService.updateById(equipment);
		return Result.ok();
	}
	
	/**
	 * 自动生成设备ID
	 * @return
	 */
	private String getDeviceId() {
		int total = equipmentService.count();
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		// 获取当前秒
		int second = calendar.get(Calendar.SECOND);
		StringBuilder id = new StringBuilder().append(year).append(second).append("011").append(total);
		return id.toString();
	}
}
