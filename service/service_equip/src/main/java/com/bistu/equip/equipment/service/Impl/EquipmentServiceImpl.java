package com.bistu.equip.equipment.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.bistu.equip.equipment.mapper.EquipmentMapper;
import com.bistu.equip.equipment.service.EquipmentService;
import com.bistu.equip.model.equipment.Equipment;
import com.bistu.equip.vo.equip.EquipmentQueryVo;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Map;

/**
 * @author Dx666
 * @Description
 * @Date 2021/7/31 - 16:25
 */
@Service
public class EquipmentServiceImpl extends ServiceImpl<EquipmentMapper, Equipment> implements EquipmentService {
	
	
	/**
	 * 分页查询设备信息
	 * @param pageParam
	 * @param equipmentQueryVo
	 * @return
	 */
	@Override
	public IPage<Equipment> selectPage(Page<Equipment> pageParam, EquipmentQueryVo equipmentQueryVo) {
		
		// 获取参数
		String brandName = equipmentQueryVo.getBrandName();
		Date buyDate = equipmentQueryVo.getBuyDate();
		String deviceId = equipmentQueryVo.getDeviceId();
		String itemNo = equipmentQueryVo.getItemNo();
		String name = equipmentQueryVo.getName();
		Double price = equipmentQueryVo.getPrice();
		Integer status = equipmentQueryVo.getStatus();
		Integer type = equipmentQueryVo.getType();
		
		QueryWrapper<Equipment> wrapper = new QueryWrapper<>();
		// 创建查询条件，并判断参数是否为空
		
		if(!StringUtils.isEmpty(brandName)) {
			wrapper.eq("brand_name", brandName);
		}
		
		if(!StringUtils.isEmpty(buyDate)) {
			wrapper.eq("buy_date", buyDate);
		}
		
		if(!StringUtils.isEmpty(deviceId)) {
			wrapper.eq("device_id", deviceId);
		}
		
		if(!StringUtils.isEmpty(itemNo)) {
			wrapper.eq("item_no", itemNo);
		}
		
		if(!StringUtils.isEmpty(name)) {
			wrapper.eq("name", name);
		}
		
		if(!StringUtils.isEmpty(price)) {
			wrapper.eq("price", price);
		}
		
		if(!StringUtils.isEmpty(status)) {
			wrapper.like("status", status);
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
	 * Equipment类中的设备类型信息
	 * @param equipment
	 */
	private void packageEquip(Equipment equipment) {
		String equipType = "";
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
		equipment.getParam().put("equipType", equipType);
	}
}
