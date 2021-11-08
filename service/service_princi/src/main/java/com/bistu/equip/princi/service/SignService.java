package com.bistu.equip.princi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bistu.equip.model.principal.PrincipalInfoFront;
import com.bistu.equip.model.principal.Sign;
import org.springframework.stereotype.Service;

/**
 * @author Dx666
 * @Description
 * @Date 2021/11/6 - 10:26
 */
public interface SignService extends IService<Sign> {
	/**
	 * 通过principalI查询一条记录
	 * @param principalId
	 * @return
	 */
	Sign getByPrincipalId(Long principalId);

}
