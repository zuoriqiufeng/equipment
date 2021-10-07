package com.bistu.equip.equipment.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.bistu.equip.equipment.mapper.EquipmentMapper;
import com.bistu.equip.model.equipment.Equipment;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @author Dx666
 * @Description
 * @Date 2021/9/17 - 13:55
 */
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentListener extends AnalysisEventListener<Equipment> {
	
	
	@Autowired
	private EquipmentMapper equipmentMapper;
	
	// 读取表头数据
	@Override
	public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
	}
	
	
	// 读取行数据，从第二行开始
	@Override
	public void invoke(Equipment equipment, AnalysisContext analysisContext) {
		// 调用方法添加到数据库当中
		equipmentMapper.insert(equipment);
	}
	
	@Override
	public void doAfterAllAnalysed(AnalysisContext analysisContext) {
	
	}
}
