package com.bistu.equip.msm.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Dx666
 * @Description
 * @Date 2021/9/21 - 14:40
 */
@Component
public class ConstantPropertiesUtils implements InitializingBean {
	
	
	@Value("${wx.open.app_id}")
	private   String appId;
	@Value("${wx.open.app_secret}")
	private  String appSecret;
	@Value("wx.open.template_id")
	private String templateId;
	
	public static String APP_ID;
	
	public static String APP_SECRET;
	
	public static String TEMPLATE_ID;
	
	
	@Override
	public void afterPropertiesSet() throws Exception {
		APP_ID = appId;
		APP_SECRET = appSecret;
		TEMPLATE_ID = templateId;
	}
}
