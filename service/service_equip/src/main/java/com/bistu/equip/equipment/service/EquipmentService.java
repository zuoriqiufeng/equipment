package com.bistu.equip.equipment.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import com.bistu.equip.model.equipment.Equipment;
import com.bistu.equip.vo.equip.EquipmentQueryVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;


/**
 * @author Dx666
 * @Description
 * @Date 2021/7/31 - 16:22
 */
public interface EquipmentService extends IService<Equipment> {
	
	// 分页查询设备信息
	IPage<Equipment> selectPage(Page<Equipment> pageParam, EquipmentQueryVo equipmentQueryVo);
	
	// 导入设备信息
	void importEquipData(MultipartFile file);
	
	
	/**
	 * 导出设备信息
	 * @param response
	 */
	void exportEquipData(HttpServletResponse response) throws IOException;
	// 保存设备信息
	
}
