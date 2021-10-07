package com.bistu.equip.user.utils;

import com.bistu.equip.user.service.UserInfoService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

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
	
	@Value("${wx.open.redirect_url}")
	private String redirectUrl;
	
	private String baseUrl;
	
	
	public static String WX_OPEN_APP_ID;
	public static String WX_OPEN_APP_SECRET;
	public static String WX_OPEN_REDIRECT_URL;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		WX_OPEN_APP_ID = appId;
		WX_OPEN_APP_SECRET = appSecret;
		WX_OPEN_REDIRECT_URL = redirectUrl;
	}
	
}
