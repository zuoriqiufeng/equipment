package com.bistu.equip.princi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bistu.equip.model.principal.PrincipalInfoFront;
import com.bistu.equip.model.principal.Sign;
import com.bistu.equip.princi.mapper.SignMapper;
import com.bistu.equip.princi.service.SignService;
import org.springframework.stereotype.Service;

/**
 * @author Dx666
 * @Description
 * @Date 2021/11/6 - 10:26
 */
@Service
public class SignServiceImpl extends ServiceImpl<SignMapper, Sign> implements SignService {
	
	@Override
	public Sign getByPrincipalId(Long principalId) {
		Sign sign = baseMapper.getByPrincipalId(principalId);
		return sign;
	}

}
