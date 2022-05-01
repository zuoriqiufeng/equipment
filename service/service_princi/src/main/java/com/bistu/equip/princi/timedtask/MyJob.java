//package com.bistu.equip.princi.timedtask;
//
//
//
//
//import com.bistu.equip.model.principal.PrincipalInfo;
//import com.bistu.equip.model.principal.ReturnRecord;
//import com.bistu.equip.model.template.WxMssVo;
//import com.bistu.equip.model.user.UserInfo;
//import com.bistu.equip.princi.delay.DelayPushUser;
//import com.bistu.equip.princi.service.PrincipalService;
//import com.bistu.equip.princi.service.ReturnRecordService;
//import com.bistu.equip.princi.service.UtilService;
//import com.bistu.equip.princi.service.impl.PrincipalServiceImpl;
//import com.bistu.equip.princi.service.impl.ReturnRecordServiceImpl;
//import com.bistu.equip.princi.service.impl.UtilServiceImpl;
//import com.bistu.equip.princi.utils.WeChatLoginUtils;
//import io.netty.util.Timeout;
//import io.netty.util.TimerTask;
//import lombok.extern.slf4j.Slf4j;
//
//import org.quartz.Job;
//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;
//import org.springframework.boot.autoconfigure.batch.BatchProperties;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.concurrent.DelayQueue;
//
///**
// *
// * @author Dx666
// * @Description
// * @Date 2021/11/5 - 19:21
// */
//@Slf4j
//@Component
//public class MyJob implements Job {
//
//
//	private UtilServiceImpl utilService = new UtilServiceImpl();
//
//	private PrincipalServiceImpl principalService = new PrincipalServiceImpl();
//
//
//	private ReturnRecordServiceImpl returnRecordService = new ReturnRecordServiceImpl();
//
//	private DelayQueue<DelayPushUser> delayQueueThree = new DelayQueue<>();
//
//	private DelayQueue<DelayPushUser> delayQueueTwo = new DelayQueue<>();
//
//	private DelayQueue<DelayPushUser> delayQueueOne = new DelayQueue<>();
//
//	/**
//	 * 定时任务
//	 */
//	public void doJob() {
//		log.info("扫描数据库查看订单信息.......");
//		System.out.println(returnRecordService);
//		List<ReturnRecord> allInfo = returnRecordService.getToReturned();
//		if(allInfo == null) {
//			return;
//		}
//		for(ReturnRecord  returnRecord : allInfo) {
//			if(returnRecord.getStatus() == 0 && returnRecord.getReturnCount() >= 3) {
//				PrincipalInfo principal = principalService.getById(returnRecord.getPrincipalId());
//				UserInfo userInfo = utilService.getUserInfo(principal.getUid());
//				// 发送消息
//				WxMssVo wxMssVo = new WxMssVo();
//				wxMssVo.setTemplateId(WeChatLoginUtils.TEMPLATE_ID);
//				wxMssVo.setOpenId(userInfo.getOpenIdApplet());
//				wxMssVo.setPage("pages/search/index");
//				DelayPushUser delayPushUser = new DelayPushUser();
//				delayPushUser.setWxMssVo(wxMssVo);
//				// 将要发送的送入延迟队列
//				switch (returnRecord.getReturnCount()) {
//					case 1:
//						delayPushUser.setTtl(3600 * 1000L);
//						delayQueueOne.offer(delayPushUser);
//						break;
//					case 2:
//						delayPushUser.setTtl(7200 * 1000L);
//						delayQueueTwo.offer(delayPushUser);
//						break;
//					case 3:
//						delayPushUser.setTtl(10800 * 1000L);
//						delayQueueThree.offer(delayPushUser);
//						break;
//					default:
//						break;
//				}
//			}
//		}
//	}
//
//
//
//	@Override
//	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
//		log.info("扫描数据库查看订单信息.......");
//		System.out.println(returnRecordService);
//		List<ReturnRecord> allInfo = returnRecordService.getToReturned();
//		if(allInfo == null) {
//			return;
//		}
//		for(ReturnRecord  returnRecord : allInfo) {
//			if(returnRecord.getStatus() == 0 && returnRecord.getReturnCount() >= 3) {
//				PrincipalInfo principal = principalService.getById(returnRecord.getPrincipalId());
//				UserInfo userInfo = utilService.getUserInfo(principal.getUid());
//				// 发送消息
//				WxMssVo wxMssVo = new WxMssVo();
//				wxMssVo.setTemplateId(WeChatLoginUtils.TEMPLATE_ID);
//				wxMssVo.setOpenId(userInfo.getOpenIdApplet());
//				wxMssVo.setPage("pages/search/index");
//				DelayPushUser delayPushUser = new DelayPushUser();
//				delayPushUser.setWxMssVo(wxMssVo);
//				// 将要发送的送入延迟队列
//				switch (returnRecord.getReturnCount()) {
//					case 1:
//						delayPushUser.setTtl(3600 * 1000L);
//						delayQueueOne.offer(delayPushUser);
//						break;
//					case 2:
//						delayPushUser.setTtl(7200 * 1000L);
//						delayQueueTwo.offer(delayPushUser);
//						break;
//					case 3:
//						delayPushUser.setTtl(10800 * 1000L);
//						delayQueueThree.offer(delayPushUser);
//						break;
//					default:
//						break;
//				}
//			}
//		}
//	}
//
//}
