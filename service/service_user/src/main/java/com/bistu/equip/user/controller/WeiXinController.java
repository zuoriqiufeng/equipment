package com.bistu.equip.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.bistu.equip.common.result.Result;
import com.bistu.equip.user.utils.HttpClientUtils;
import com.bistu.equip.user.utils.WeChatLoginUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Dx666
 * @Description
 * @Date 2021/11/8 - 8:18
 */
@RestController
@RequestMapping("/admin/user/weixin")
public class WeiXinController {
	
	
	
	/**
	 * 获取生成二位吗所需要的参数
	 * @return
	 */
	@GetMapping("getLoginParam")
	public Result getQrConnect() {
		try{
			Map<String, Object> map = new HashMap<>();
			map.put("appid", WeChatLoginUtils.WX_OPEN_APP_ID);
			map.put("scope", "snsap_login");
			String wxOpenRedirectUrl = WeChatLoginUtils.WX_OPEN_REDIRECT_URL;
			wxOpenRedirectUrl = URLEncoder.encode(wxOpenRedirectUrl, "utf-8");
			map.put("redirectUri", wxOpenRedirectUrl);
			map.put("state", System.currentTimeMillis());
			return Result.ok(map);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 微信扫码后的回调方法
	 * @param code
	 * @param state
	 * @return
	 */
	@GetMapping("callback")
	public String callback(String code, String state) {
		// 1.获取临时票据code
		// 2.拿着code 和微信id和密钥，请求微信固定地址，得到两个值
		// 使用code 和appid以及appscrect 换取access_token
		// %s 就是占位符
		StringBuffer  baseAccessTokenUrl = new StringBuffer()
				.append("https://api.weixin.qq.com/sns/oauth2/access_token")
				.append("?appid=%s")
				.append("&secret=%s")
				.append("&code=%s")
				.append("&grant_type=authorization_code");
		String accessTokenUrl = String.format(
				baseAccessTokenUrl.toString(),
				WeChatLoginUtils.WX_OPEN_APP_ID,
				WeChatLoginUtils.WX_OPEN_APP_SECRET,
				code);
		try{
			String accessTokenInfo = HttpClientUtils.get(accessTokenUrl);
			// 从返回的字符串中获取openid和access_token
			JSONObject jsonObject = JSONObject.parseObject(accessTokenInfo);
			String access_token = jsonObject.getString("access_token");
			String openid = jsonObject.getString("openid");
			// 3 拿着openid 和 access_token 请求微信地址，得到扫描人信息
			String baseUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo" + "?access_token=%s" + "&openid=%s";
			baseUserInfoUrl = String.format(baseUserInfoUrl, access_token, openid);
			// 获取到用户信息
			String resultUserInfoJson = HttpClientUtils.get(baseUserInfoUrl);
			
			
			
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
