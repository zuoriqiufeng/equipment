package com.bistu.equip.vo.equip;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.EAN;

import java.util.Date;

/**
 * 设备excel数据
 * @author Dx666
 * @Description
 * @Date 2021/10/10 - 15:00
 */
@Data
public class EquipmentEeVo {
	@ExcelProperty(value = "设备编号")
	private String deviceId;
	
	@ExcelProperty(value = "设备名称")
	private String name;
	
	@ExcelProperty(value = "负责人")
	private String principal;
	
	@ExcelProperty(value = "品牌名")
	private String brandName;
	
	@ExcelProperty(value = "价格")
	private Double price;
	
	@ExcelProperty(value = "价值")
	private Double value;
	
	@ExcelProperty(value = "设备类型")
	private String type;
	
	@ExcelProperty(value = "设备状态")
	private String status;
	
	@ExcelProperty(value = "采购日期")
	private Date buyDate;
	
	@ExcelProperty(value = "经费来源")
	private String moneySource;
	
	@ExcelProperty(value = "项目号")
	private String itemNo;
	
	@ExcelProperty(value = "资产系统_验收单编号")
	private String eAmReceiptNo;
	
	@ExcelProperty(value = "资产系统_资产分类")
	private String eAmClassification;
	
	@ExcelProperty(value = "资产系统_资产编号")
	private String eAmNo;
	
	@ExcelProperty(value = "资产系统_资产名称")
	private String eAmName;
	
	@ExcelProperty(value = "资产系统_取得日期")
	private Date eAmGainDate;
	
	@ExcelProperty(value = "资产系统_使用人")
	private String eAmUser;
	
	@ExcelProperty(value = "资产系统_管理部门")
	private String eAmDeptManage;
	
	@ExcelProperty(value = "资产系统_存放地点")
	private String eAmDepositArea;
	
	@ExcelProperty(value = "资产系统_采购组织形式")
	private String eAmBuyForm;
	
	@ExcelProperty(value = "资产系统_记账日期")
	private Date eAmChargeDate;
	
	@ExcelProperty(value = "资产系统_具体位置")
	private String eAmDefiniteArea;
}
