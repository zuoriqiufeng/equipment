package com.bistu.equip.msm.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 *
 * 实现MyBatis-Plus的自动填充
 * @author Dx666
 * @Description
 * @Date 2021/9/29 - 16:21
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
	@Override
	public void insertFill(MetaObject metaObject) {
		log.info("start insert fill.....");
		this.setFieldValByName("createTime", new Date(), metaObject);
		this.setFieldValByName("updateTime", new Date(), metaObject);
	}
	
	@Override
	public void updateFill(MetaObject metaObject) {
		log.info("start update fill......");
		this.setFieldValByName("updateTime", new Date(), metaObject);
	}

}
