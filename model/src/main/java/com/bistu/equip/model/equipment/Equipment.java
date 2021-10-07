package com.bistu.equip.model.equipment;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import com.baomidou.mybatisplus.annotation.Version;
import com.bistu.equip.model.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author Dx666
 * @Description
 * @Date 2021/7/30 - 20:59
 */
@Data
@ApiModel(description = "equipment")
@TableName("equipment")
@ToString
public class Equipment extends BaseEntity {
	@ApiModelProperty(value = "设备编号")
	@TableField("device_Id")
	private String deviceId;
	
	@ApiModelProperty(value = "设备名称")
	@TableField("name")
	private String name;
	
	@ApiModelProperty(value = "负责人")
	@TableField("principal")
	private String principal;
	
	@ApiModelProperty(value = "品牌名")
	@TableField("brand_name")
	private String brandName;
	
	@ApiModelProperty(value = "价格")
	@TableField("price")
	private Double price;
	
	@ApiModelProperty(value = "价值")
	@TableField("value")
	private Double value;
	
	@ApiModelProperty(value = "类型(0: 个人设备 1: 未入库设备 2: 已入库设备)")
	@TableField("type")
	private Integer type;
	
	@ApiModelProperty(value = "设备状态(0:空闲 1：使用中 2：已损坏)")
	@TableField("status")
	private Integer status;
	
	@ApiModelProperty(value = "采购日期")
	@TableField("buy_date")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date buyDate;
	
	@ApiModelProperty(value = "经费来源")
	@TableField("money_source")
	private String moneySource;
	
	@ApiModelProperty(value = "项目号")
	@TableField("item_no")
	private String itemNo;
	
	@ApiModelProperty(value = "资产系统_验收单编号")
	@TableField("EAM_receipt_no")
	private String eAmReceiptNo;
	
	@ApiModelProperty(value = "资产系统_资产分类")
	@TableField("EAM_classification")
	private String eAmClassification;
	
	@ApiModelProperty(value = "资产系统_资产编号")
	@TableField("EAM_no")
	private String eAmNo;
	
	@ApiModelProperty(value = "资产系统_资产名称")
	@TableField("EAM_name")
	private String eAmName;
	
	@ApiModelProperty(value = "资产系统_取得日期")
	@TableField("EAM_gain_date")
	private Date eAmGainDate;
	
	@ApiModelProperty(value = "资产系统_使用人")
	@TableField("EAM_user")
	private String eAmUser;
	
	@ApiModelProperty(value = "资产系统_管理部门")
	@TableField("EAM_dept_manage")
	private String eAmDeptManage;
	
	@ApiModelProperty(value = "资产系统_存放地点")
	@TableField("EAM_deposit_area")
	private String eAmDepositArea;
	
	@ApiModelProperty(value = "资产系统_采购组织形式")
	@TableField("EAM_buy_form")
	private String eAmBuyForm;
	
	@ApiModelProperty(value = "资产系统_记账日期")
	@TableField("EAM_charge_date")
	private Date eAmChargeDate;
	
	@ApiModelProperty(value = "资产系统_具体位置")
	@TableField("EAM_definite_area")
	private String eAmDefiniteArea;
	
//	@Version
//	@TableField
//	private Integer version;
}
