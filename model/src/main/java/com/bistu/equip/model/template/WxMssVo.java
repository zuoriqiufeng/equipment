package com.bistu.equip.model.template;

import lombok.Data;

import java.util.Date;
import java.util.Map;

/**
 *
 * 小程序消息推送，归还设备的模板消息类
 * @author Dx666
 * @Description
 * @Date 2021/9/23 - 19:30
 */
@Data
public class WxMssVo {
	/**
	 * 模板消息id
	 *
	 */
	private String templateId;
	
	private String page = "page/search/index";
	
	private String touser;
	
	private String openId;
	
	private String equipName;
	
	private String equipNo;
	
	private Date borrowTime;
	
	private Date returnTime;
	
	private String tips = "请按尽快按要求归还至制定位置";
	
}
