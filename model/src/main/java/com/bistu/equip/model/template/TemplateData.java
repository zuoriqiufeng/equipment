package com.bistu.equip.model.template;

import lombok.Data;

/**
 * 模板消息数据
 * @author Dx666
 * @Description
 * @Date 2021/12/2 - 20:35
 */
@Data
public class TemplateData {
	/**
	 * 模板消息推送值。包括时间，归还信息等
	 */
	private String value;
	
	public TemplateData(String value) {
		this.value = value;
	}
	

}
