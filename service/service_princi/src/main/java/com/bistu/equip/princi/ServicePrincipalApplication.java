package com.bistu.equip.princi;



import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import org.springframework.context.annotation.ComponentScan;


import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Date;
import java.util.Timer;

import static org.quartz.TriggerBuilder.newTrigger;

/**
 *
 * @author Dx666
 * @Description
 * @Date 2021/8/8 - 17:1
 */
@ComponentScan("com.bistu.equip")
@SpringBootApplication
@EnableSwagger2
//@CrossOrigin // 设置跨域访问
@EnableDiscoveryClient // 注册nacos服务
@EnableFeignClients("com.bistu.equip")
public class ServicePrincipalApplication {

	public static void main(String[] args) throws SchedulerException {
		SpringApplication.run(ServicePrincipalApplication.class, args);
//
//		//1.创建Scheduler的工厂
				
				//2.从工厂中获取调度器实例
//				Scheduler scheduler =  StdSchedulerFactory.getDefaultScheduler();
//				//3.创建JobDetail，
//				JobDetail jb = JobBuilder.newJob(MyJob.class)
//						//job的描述
//						.withDescription("this is a ram job")
//						//job 的name和group
//						.withIdentity("ramJob", "ramGroup")
//						.build();
//				//4.创建Trigger
//				//5.创建触发器10之后开始执行 每3秒钟执行一次
//				Trigger trigger = org.quartz.TriggerBuilder
//						.newTrigger()
//						.withIdentity("trigger1", "group3")
//						.startAt(new Date(System.currentTimeMillis() + 10000))
//						.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInHours(24).repeatForever())
//						.build();
//				//6.注册任务和定时器
//				scheduler.scheduleJob(jb, trigger);
//				//7.启动 调度器
//				scheduler.start();
//		PushMessage pushMessage = new PushMessage();
//		pushMessage.job();
	}
	
	
}
