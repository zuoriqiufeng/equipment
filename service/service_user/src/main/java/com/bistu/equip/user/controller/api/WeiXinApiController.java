package com.bistu.equip.user.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.bistu.equip.common.helper.JwtHelper;
import com.bistu.equip.common.result.Result;
import com.bistu.equip.model.user.UserInfo;
import com.bistu.equip.user.service.UserInfoService;
import com.bistu.equip.user.utils.HttpClientUtils;
import com.bistu.equip.user.utils.WeChatLoginUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;

/**
 * @author Dx666
 * @Description
 * @Date 2021/8/22 - 14:23
 */
@RestController
@RequestMapping("api/user/weixin")
@ResponseBody
public class WeiXinApiController {
	
	
	@Autowired
	public UserInfoService userInfoService;
	
	@ApiOperation("用户微信登录回调接口")
	@PostMapping("login")
	public Result login(@RequestBody String code) {
		String openId = WeChatLoginUtils.WX_OPEN_APP_ID;
		String secret = WeChatLoginUtils.WX_OPEN_APP_SECRET;
		StringBuffer url = new StringBuffer()
				.append("https://api.weixin.qq.com/sns/jscode2session?")
				.append("appid" +
				"=%s")
				.append("&secret=%s")
				.append("&js_code=%s")
				.append("&grant_type=authorization_code");
		String accessUrl = String.format(
				url.toString(),
				openId,
				secret,
				code);
		try {
			String accessTokenInfo = HttpClientUtils.get(accessUrl);
			// 从返回字符串中获取两个值 openid 和session_token errcode errmsg的信息
			JSONObject jsonObject = JSONObject.parseObject(accessTokenInfo);
			String session_key = jsonObject.getString("session_key");
			String openid = jsonObject.getString("openid");
			String errcode = jsonObject.getString("errcode");
			String errmsg = jsonObject.getString("errmsg");
			
			// 判断是否请求成功
			if(openId == null || session_key == null) {
				return Result.fail("errcode: " + errcode + ";errmsg: " + errmsg);
			}
			UserInfo userInfo = userInfoService.selectWxInfoOpenId(openid);
			// 存储返回信息
			HashMap<String, String> map = new HashMap<>();
			// 表示用户第一次登陆
			if(userInfo == null) {
				userInfo = new UserInfo();
				userInfo.setOpenId(openId);
				userInfo.setLastLoginTime(new Date());
				userInfo.setCreateTime(new Date());
				userInfo.setUpdateTime(new Date());
				userInfoService.save(userInfo);
			}
			map.put("openid", openid);
			map.put("session_key", session_key);
			String name = userInfo.getName();
			if(StringUtils.isEmpty(name)) {
				map.put("name", "bistu_" + new Date().getDate());
			}
			// 生成token字符串
			String token = JwtHelper.createToken(userInfo.getId(), map.get("name"));
			map.put("token", token);
			return Result.ok(map);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Result.ok();
	}
	
	
}
