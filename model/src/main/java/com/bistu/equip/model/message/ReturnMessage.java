package com.bistu.equip.model.message;

import lombok.Data;

/**
 * @author Dx666
 * @Description
 * @Date 2021/9/23 - 19:32
 */
@Data
public class ReturnMessage {
	
	/**
	 * 调用凭证
	 * 用户openId
	 * 小程序secretId
	 * 订阅消息模板Id
	 * 点击之后跳转的页面
	 * 模板推送消息的模板
	 */
	private String accessToken;
	private String openId;
	private String secretId;
	private String templateId;
	private String page;
	private ReturnMessage returnMessage;
	
}
