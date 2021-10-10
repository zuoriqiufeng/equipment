package com.bistu.equip.equipment.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.bistu.equip.equipment.mapper.EquipmentMapper;
import com.bistu.equip.model.equipment.Equipment;
import com.bistu.equip.vo.equip.EquipmentEeVo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @author Dx666
 * @Description
 * @Date 2021/9/17 - 13:55
 */
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentListener extends AnalysisEventListener<EquipmentEeVo> {
	
	
	@Autowired
	private EquipmentMapper equipmentMapper;
	
	// 读取表头数据
	@Override
	public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
		log.info("导入设备数据，读取Excel表格头数据......");
	}
	
	
	// 读取行数据，从第二行开始
	@Override
	public void invoke(EquipmentEeVo equipmentEeVo, AnalysisContext analysisContext) {
		log.info("导入设备数据，读取Excel表格行数据......");
		Equipment equipment = new Equipment();
		// 调用方法添加到数据库当中
		BeanUtils.copyProperties(equipmentEeVo, equipment);
		this.packageEquip(equipment, equipmentEeVo);
		equipmentMapper.insert(equipment);
	}
	
	@Override
	public void doAfterAllAnalysed(AnalysisContext analysisContext) {
	
	}
	
	/**
	 * 封装导入数据
	 */
	private void packageEquip(Equipment equipment, EquipmentEeVo equipmentEeVo) {
		if("个人设备".equals(equipmentEeVo.getType())) {
			equipment.setType(0);
		} else if("未入库设备".equals(equipmentEeVo.getType())) {
			equipment.setType(1);
		} else {
			equipment.setType(2);
		}
		if("空闲".equals(equipmentEeVo.getStatus())) {
			equipment.setStatus(0);
		} else if("使用中".equals(equipmentEeVo.getStatus())) {
			equipment.setStatus(1);
		} else {
			equipment.setStatus(2);
		}
	}
}
