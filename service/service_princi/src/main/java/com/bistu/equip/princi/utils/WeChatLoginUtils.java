package com.bistu.equip.princi.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Dx666
 * @Description
 * @Date 2021/8/22 - 14:40
 */
@Component
public class WeChatLoginUtils implements InitializingBean {

	@Value("${wx.open.app_id}")
	private String appId;
	
	@Value("${wx.open.app_secret}")
	private String appSecret;
	
	@Value("${template_id}")
	private String templateId;
	
	
	public static String WX_OPEN_APP_ID;
	public static String WX_OPEN_APP_SECRET;
	public static String TEMPLATE_ID;
	
	
	@Override
	public void afterPropertiesSet() throws Exception {
		WX_OPEN_APP_ID = appId;
		WX_OPEN_APP_SECRET = appSecret;
		TEMPLATE_ID = templateId;
	}
	
}
