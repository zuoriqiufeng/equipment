package com.bistu.equip.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.bistu.equip.common.helper.JwtHelper;
import com.bistu.equip.common.result.Result;
import com.bistu.equip.model.user.UserInfo;
import com.bistu.equip.user.service.UserInfoService;
import com.bistu.equip.user.utils.HttpClientUtils;
import com.bistu.equip.user.utils.WeChatLoginUtils;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Dx666
 * @Description
 * @Date 2021/11/8 - 8:18
 */
@Controller
@RequestMapping("/admin/user/weixin")
@ResponseBody // 支持response返回
public class WeiXinController {
	
	
	@Autowired
	private UserInfoService userInfoService;
	/**
	 * 获取生成二位吗所需要的参数
	 * @return
	 */
	@GetMapping("getLoginParam")
	public Result getQrConnect() {
		try{
			Map<String, Object> map = new HashMap<>();
			map.put("appid", WeChatLoginUtils.WX_OPEN_APP_ID);
			map.put("scope", "snsapi_login");
			String wxOpenRedirectUrl = WeChatLoginUtils.WX_OPEN_REDIRECT_URL;
			wxOpenRedirectUrl = URLEncoder.encode(wxOpenRedirectUrl, "utf-8");
			map.put("redirectUri", wxOpenRedirectUrl);
			map.put("state", System.currentTimeMillis());
			System.out.println(map.get("redirectUri"));
			return Result.ok(map);
		} catch (Exception e) {
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
	public void callback(String code, String state, HttpServletResponse response) {
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
			JSONObject resultInfo = JSONObject.parseObject(resultUserInfoJson);
			
			// 获取到解析后的用户信息
			// 昵称
			String nickname = resultInfo.getString("nickname");
			// unionid
			String unionId = resultInfo.getString("unionid");
			UserInfo userInfo = userInfoService.selectWxInfoUnionId(unionId);
			// 用户第一次登录
			if(userInfo == null) {
				userInfo = new UserInfo();
				userInfo.setUnionId(unionId);
				userInfo.setNickName(nickname);
				userInfo.setOpenIdApp(openid);
				userInfo.setLastLoginTime(new Date());
				userInfo.setCreateTime(new Date());
				userInfo.setUpdateTime(new Date());
				userInfoService.save(userInfo);
			}
			// 存储返回信息
			
			HashMap<String, String> map = new HashMap<>();
			map.put("openid", openid);
			String name = userInfo.getName();
			if(StringUtils.isEmpty(name)) {
				map.put("name", "bistu_" + new Date().getDate());
			}
			// 生成token字符串
			String token = JwtHelper.createToken(userInfo.getId(), map.get("name"));
			map.put("token", token);
			response.sendRedirect( WeChatLoginUtils.EQUIP_BASE_URL
					+ "/#/callback?token="
					+ map.get("token")
					+ "&openid="
					+ map.get("openid")
					+ "&name="
					+ URLEncoder.encode(map.get("name"), "utf-8"));
//			return "redirect:"
//					+ WeChatLoginUtils.EQUIP_BASE_URL
//					+ "/#/users?token="
//					+ map.get("token")
//					+ "&openid="
//					+ map.get("openid")
//					+ "&name="
//					+ URLEncoder.encode(map.get("name"), "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
//			return null;
		}
	}
}
