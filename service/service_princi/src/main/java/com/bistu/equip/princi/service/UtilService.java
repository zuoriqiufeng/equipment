package com.bistu.equip.princi.service;

import com.bistu.equip.model.user.UserInfo;
import com.bistu.equip.user.client.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Dx666
 * @Description
 * @Date 2021/12/5 - 9:50
 */
@Service
public interface UtilService {
	
	/**
	 * 获取用户信息
	 * @param id
	 * @return
	 */
	UserInfo getUserInfo(Long id);
}
