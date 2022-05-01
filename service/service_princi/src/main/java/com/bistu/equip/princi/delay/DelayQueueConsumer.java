package com.bistu.equip.princi.delay;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.DelayQueue;


/**
 * @author Dx666
 * @Description
 * @Date 2021/12/3 - 8:39
 */
@Slf4j
public class DelayQueueConsumer implements Runnable{
	
	private final DelayQueue<DelayPushUser> delayQueue;
	/**
	 * 构造函数
	 * @param delayQueue 延迟队列
	 */
	public DelayQueueConsumer(DelayQueue<DelayPushUser> delayQueue) {
		this.delayQueue = delayQueue;
	}
	@Override
	public void run() {
		while (true) {
			try {
				// 从延迟队列的头部获取已经过期的消息
				// 如果暂时没有过期消息或者队列为空，则take()方法会被阻塞，直到有过期的消息为止
				DelayPushUser delayPushUser = delayQueue.take();
				// 推送消息
				delayPushUser.pushOneUser();
				log.info("消息推送成功!");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
