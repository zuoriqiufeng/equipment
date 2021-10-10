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
import com.bistu.equip.vo.equip.EquipmentEeVo;
import com.bistu.equip.vo.equip.EquipmentQueryVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Dx666
 * @Description
 * @Date 2021/7/31 - 16:25
 */
@Slf4j
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
			System.out.println(file);
			log.info("表格数据读取中......");
			EasyExcel.read(file.getInputStream(), EquipmentEeVo.class, new EquipmentListener(equipmentMapper)).sheet().doRead();
		} catch (IOException e) {
			e.printStackTrace();
		}
		log.info("设备数据导入成功......");
	}
	
	
	/**
	 * 设备数据导出
	 * @param response
	 */
	@Override
	public void exportEquipData(HttpServletResponse response) throws IOException {
		log.info("设备数据导出......");
		// 设置下载信息
		response.setContentType("application/vnd.ms-excel");
		response.setCharacterEncoding("UTF-8");
		String fileName = URLEncoder.encode("设备信息", "UTF-8");
		response.setHeader("Content-disposition", "attachment;filename=" + fileName+".xlsx");
		
		
		// 查询数据库
		List<Equipment> equipments = baseMapper.selectList(null);
		// Equipment --> EquipmentEeVo
		// 创建新的list存储
		List<EquipmentEeVo> equipmentEeVos = new ArrayList<>();
		
		for(Equipment equipment : equipments) {
			// 封装数据
			log.info("封装设备数据......");
			this.packageEquip(equipment);
			EquipmentEeVo equipmentEeVo = new EquipmentEeVo();
			BeanUtils.copyProperties(equipment, equipmentEeVo);
			// 获取到对应的字符串数据
			equipmentEeVo.setType((String)equipment.getParam().get("equipType"));
			equipmentEeVo.setStatus((String)equipment.getParam().get("statusString"));
			equipmentEeVos.add(equipmentEeVo);
		}
		
		// 将其写入excel表格中单击浏览器的导出使用浏览器的流下载
		EasyExcel.write(response.getOutputStream(), EquipmentEeVo.class)
				.sheet()
				.doWrite(equipmentEeVos);
		log.info("设备数据导出成功......");
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
