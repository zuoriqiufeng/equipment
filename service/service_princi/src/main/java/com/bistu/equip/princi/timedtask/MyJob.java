package com.bistu.equip.princi.timedtask;

import com.bistu.equip.model.principal.PrincipalInfo;
import com.bistu.equip.model.principal.ReturnRecord;
import com.bistu.equip.princi.service.PrincipalService;
import com.bistu.equip.princi.service.ReturnRecordService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *
 * @author Dx666
 * @Description
 * @Date 2021/11/5 - 19:21
 */
@Slf4j
@Component
public class MyJob implements Job {
	
	@Autowired
	private PrincipalService principalService;
	
	@Autowired
	private ReturnRecordService returnRecordService;
	
	/**
	 * 扫描数据库任务
	 * @param jobExecutionContext
	 * @throws JobExecutionException
	 */
	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		log.info("扫描数据库查看订单信息.......");
		List<ReturnRecord> allInfo = returnRecordService.getToReturned();
		
	}
}
