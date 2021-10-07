package com.bistu.equip.msm.timer;

import com.bistu.equip.common.result.Result;
import com.bistu.equip.model.principal.PrincipalInfo;
import com.bistu.equip.princi.client.PrinciFeignClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.TimerTask;

/**
 * 定时查看数据库，更新数据库的借用时间
 *
 * @author Dx666
 * @Description
 * @Date 2021/9/22 - 8:57
 */
public class UpdateBorrowTime extends TimerTask {
	
	@Autowired
	private PrinciFeignClient princiFeignClient;
	
	@Override
	public void run() {
		Result all = princiFeignClient.getAllData();
		List<PrincipalInfo> data = (List<PrincipalInfo>)all.getData();
		for(int i = 0; i< data.size(); i++) {
			PrincipalInfo principalInfo = data.get(i);
			
		}
		
	}
}
