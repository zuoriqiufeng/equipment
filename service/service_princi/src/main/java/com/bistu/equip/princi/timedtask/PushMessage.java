//package com.bistu.equip.princi.timedtask;
//
//import org.quartz.*;
//import org.quartz.impl.StdScheduler;
//import org.quartz.impl.StdSchedulerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.Date;
//
///**
// * @author Dx666
// * @Description
// * @Date 2021/12/2 - 20:41
// */
//
//public class PushMessage {
//
//
//	@Autowired
//	private Scheduler scheduler;
//
//	public void job() throws SchedulerException {
//	 //1.创建Scheduler的工厂
////	 SchedulerFactory sf = new StdSchedulerFactory();
////	 //2.从工厂中获取调度器实例
////	 Scheduler scheduler = sf.getScheduler();
//	 //3.创建JobDetail，
//	 JobDetail jb = JobBuilder.newJob(MyJob.class)
//			 //job的描述
//			 .withDescription("this is a ram job")
//			 //job 的name和group
//			 .withIdentity("ramJob", "ramGroup")
//			 .build();
//	 //4.创建Trigger
//	 //5.创建触发器10之后开始执行 每3秒钟执行一次
//	 Trigger trigger = TriggerBuilder
//			 .newTrigger()
//			 .withIdentity("trigger1", "group3")
//			 .startAt(new Date(System.currentTimeMillis() + 10000))
//			 .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInHours(24).repeatForever())
//			 .build();
//	 //6.注册任务和定时器
//	 this.scheduler.scheduleJob(jb, trigger);
//	 //7.启动 调度器
//	 this.scheduler.start();
// }
//}
