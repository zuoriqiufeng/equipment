package com.bistu.equip.msm.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.bistu.equip.common.Exception.EquipException;
import com.bistu.equip.common.result.ResultCodeEnum;
import com.bistu.equip.msm.service.MsmService;
import com.bistu.equip.msm.utils.ConstantPropertiesUtils;
import com.bistu.equip.msm.utils.HttpClientUtils;
import org.springframework.stereotype.Service;

/**
 * @author Dx666
 * @Description
 * @Date 2021/9/21 - 14:39
 */
@Service
public class MsmServiceImpl implements MsmService {
	
	public String getAccessToken() {
		// 拼接完整的获取AccessToken的url
		StringBuffer baseAccessTokenUrl = new StringBuffer()
				.append("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=")
				.append(ConstantPropertiesUtils.APP_ID)
				.append("&secret=")
				.append(ConstantPropertiesUtils.APP_SECRET);
		// 使用httpClient请求地址
		try {
            String accessTokenInfo = HttpClientUtils.get(baseAccessTokenUrl.toString());
			// 对返回的字符串进行解析
            JSONObject jsonObject = JSONObject.parseObject(accessTokenInfo);
			String accessToken = jsonObject.getString("access_token");
			if(accessToken == null) {
				throw new EquipException(ResultCodeEnum.valueOf("FETCH_ACCESSTOKEN_FAILD"));
			}
			return accessToken;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
