package com.bistu.equip.common.Exception;


import com.bistu.equip.common.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理部分
 *
 * @author Dx666
 * @Description
 * @Date 2021/4/4 - 21:11
 */
@ControllerAdvice // 全局异常处理添加注解
@RestControllerAdvice // 表示方法返回值用json格式输出
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class) // 表示出现那种异常执行这个方法
	@ResponseBody // 结果用json格式做输出   或者在类上使用@RestControllerAdvice注解
	public Result error(Exception e) {
		e.printStackTrace();
		return Result.fail();
	}
	
	@ExceptionHandler(EquipException.class) // 表示出现那种异常执行这个方法
	@ResponseBody // 结果用json格式做输出   或者在类上使用@RestControllerAdvice注解
	public Result error(EquipException e) {
		e.printStackTrace();
		return Result.fail();
	}
	
}
