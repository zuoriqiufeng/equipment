package com.bistu.equip.princi.service.impl;

import com.bistu.equip.model.user.UserInfo;
import com.bistu.equip.princi.service.UtilService;
import com.bistu.equip.user.client.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Dx666
 * @Description
 * @Date 2021/12/5 - 9:51
 */
@Service
public class UtilServiceImpl implements UtilService {
	@Autowired
	private UserFeignClient userFeignClient;
	
	@Override
	public UserInfo getUserInfo(Long id) {
		UserInfo userInfo = userFeignClient.getUserInfo(id);
		return userInfo;
	}
}
