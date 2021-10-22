package com.bistu.equip.user.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.bistu.equip.model.user.UserInfo;

/**
 * @author Dx666
 * @Description
 * @Date 2021/10/20 - 22:32
 */
public class UserListener extends AnalysisEventListener<UserInfo> {
	@Override
	public void invoke(UserInfo userInfo, AnalysisContext analysisContext) {
	
	}
	
	@Override
	public void doAfterAllAnalysed(AnalysisContext analysisContext) {
	
	}
}
