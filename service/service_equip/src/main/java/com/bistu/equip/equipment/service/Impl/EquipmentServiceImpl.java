package com.bistu.equip.equipment.service.Impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.bistu.equip.equipment.listener.EquipmentListener;
import com.bistu.equip.equipment.mapper.EquipmentMapper;
import com.bistu.equip.equipment.service.EquipmentService;
import com.bistu.equip.model.equipment.Equipment;
import com.bistu.equip.vo.equip.EquipmentQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * @author Dx666
 * @Description
 * @Date 2021/7/31 - 16:25
 */
@Service
public class EquipmentServiceImpl extends ServiceImpl<EquipmentMapper, Equipment> implements EquipmentService  {
	
	@Autowired
	private EquipmentMapper equipmentMapper;
	/**
	 * 分页查询设备信息
	 * @param pageParam
	 * @param equipmentQueryVo
	 * @return
	 */
	@Override
	public IPage<Equipment> selectPage(Page<Equipment> pageParam, EquipmentQueryVo equipmentQueryVo) {
		// 获取参数
		String name = equipmentQueryVo.getName();
		Integer status = equipmentQueryVo.getStatus();
		Integer type = equipmentQueryVo.getType();
		
		QueryWrapper<Equipment> wrapper = new QueryWrapper<>();
		// 创建查询条件，并判断参数是否为空
		
		if(!StringUtils.isEmpty(name)) {
			wrapper.like("name", name);
		}
		if(!StringUtils.isEmpty(status)) {
			wrapper.eq("status", status);
		}
		
		if(!StringUtils.isEmpty(type)) {
			wrapper.eq("type", type);
		}
		// 查询
		Page<Equipment> equipmentPage = baseMapper.selectPage(pageParam, wrapper);
		// 封装信息
		equipmentPage.getRecords().forEach(this::packageEquip);
		
		return equipmentPage;
	}
	
	/**
	 * 批量导入设备数据
	 * @param file 外部文件
	 */
	@Override
	public void importEquipData(MultipartFile file) {
		try {
			EasyExcel.read(file.getInputStream(), Equipment.class, new EquipmentListener(equipmentMapper));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Equipment类中的设备类型信息
	 * @param equipment
	 */
	private void packageEquip(Equipment equipment) {
		String equipType = "";
		String statusString = "";
		switch (equipment.getType()) {
			case 0:
				equipType = "个人设备";
				break;
			case 1:
				equipType = "未入库设备";
				break;
			case 2:
				equipType = "已入库设备";
				break;
			default:
				break;
		}
		switch (equipment.getStatus()) {
			case 0:
				statusString = "空闲";
				break;
			case 1:
				statusString = "使用中";
				break;
			case 2:
				statusString = "已损坏";
				break;
			default:
				break;
		}
		equipment.getParam().put("equipType", equipType);
		equipment.getParam().put("statusString", statusString);
	}
}
