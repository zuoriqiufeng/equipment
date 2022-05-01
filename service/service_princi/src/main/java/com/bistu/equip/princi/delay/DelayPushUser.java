package com.bistu.equip.princi.delay;

import com.alibaba.fastjson.JSONObject;
import com.bistu.equip.model.template.TemplateData;
import com.bistu.equip.model.template.WxMssVo;
import com.bistu.equip.princi.utils.HttpClientUtils;
import com.bistu.equip.princi.utils.WeChatLoginUtils;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author Dx666
 * @Description
 * @Date 2021/12/2 - 22:17
 */

@Data
@Component
public class DelayPushUser  implements Delayed {
	private long ttl;         // 延迟任务到期时间（过期时间）
	private WxMssVo wxMssVo;// 延迟任务中的任务数据
	
	@Override
	public long getDelay(TimeUnit unit) {
		// 计算该任务距离过期还剩多少时间
		long remaining = ttl - System.currentTimeMillis();
		return unit.convert(remaining, TimeUnit.MILLISECONDS);
	}
	
	@Override
	public int compareTo(Delayed o) {
		// 比较、排序：对任务的延时大小进行排序，将延时时间最小的任务放到队列头部
		return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
	}
	
	
	/**
	 * 推送消息
	 */
	public String pushOneUser() {
		RestTemplate restTemplate = new RestTemplate();
		//这里简单起见我们每次都获取最新的access_token（时间开发中，应该在access_token快过期时再重新获取）
		String url = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=" + getAccessToken() +
		"&touser=" + wxMssVo.getTouser() + "&template_id=" + wxMssVo.getTemplateId() + "";
		//拼接推送的模版
		
		Map<String, TemplateData> m = new HashMap<>(3);
		m.put("first", new TemplateData("北京信息科技大学设备归还通知"));
		m.put("keyword1", new TemplateData(wxMssVo.getEquipName()));
		m.put("keyword2", new TemplateData(wxMssVo.getEquipName()));
		m.put("keyword3", new TemplateData(wxMssVo.getBorrowTime().toString()));
		m.put("keyword4", new TemplateData("北京信息科技大学健翔桥校区教三-303"));
		m.put("remark", new TemplateData("请尽快归还"));
		
		ResponseEntity<String> responseEntity =
				restTemplate.postForEntity(url, m, String.class);
		System.out.println(responseEntity.getBody());
		return responseEntity.getBody();
	}
	
	/**
	 * 获取access_token
	 * @return
	 */
	private String getAccessToken() {
		String openId = WeChatLoginUtils.WX_OPEN_APP_ID;
		String secret = WeChatLoginUtils.WX_OPEN_APP_SECRET;
		StringBuffer url = new StringBuffer()
				.append("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential")
				.append("appid" + "=%s")
				.append("&secret=%s");
		String accessUrl = String.format(
				url.toString(),
				openId,
				secret);
		String accessTokenInfo = null;
		String accessToken = null;
		try {
			accessTokenInfo = HttpClientUtils.get(accessUrl);
			// 从返回字符串中获取两个值 openid 和session_token errcode errmsg的信息
			JSONObject jsonObject = JSONObject.parseObject(accessTokenInfo);
			accessToken = jsonObject.getString("access_token");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accessToken;
	}
	
}
