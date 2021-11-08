package com.bistu.equip.common.result;

import lombok.Getter;

/**
 * 统一返回状态信息类
 * @author Dx666
 * @Description
 * @Date 2021/4/4 - 12:04
 */
@Getter
public enum ResultCodeEnum {
	// 返回数据成功
	SUCCESS(200, "成功"),
	FAIL(201, "失败"),
	PARAM_ERROR(202, "参数不正确"),
	SERVICE_ERROR(203, "服务异常"),
	DATA_ERROR(204, "数据异常"),
	DATA_UPDATE_ERROR(205, "数据版本异常"),
	
	LOGIN_AUTH(208, "未登录"),
	PERMISSION(208, "没有权限"),
	
	CODE_ERROR(210, "验证码错误"),
	LOGIN_DISABLED_ERROR(212, "该用户已被禁用"),
	REGISTER_MOBILE_ERROR(213, "手机号已被使用"),
	LOGIN_AURH(214, "需要登陆"),
	LOGIN_ACL(215, "没有权限"),
	
	URL_ENCODE_ERROR(216, "URL编码失败"),
	ILLEGAL_CALLBACK_REQUEST_ERROR(217, "非法回调请求"),
	FETCH_ACCESSTOKEN_FAILD(218, "获取accessToken失败"),
	FETCH_USERINFO_ERROR(219, "获取用户失败"),
	FORM_REPEAT_SUBMIT(220, "表单重复提交"),
	EQUIP_NOT_EXIST(221, "设备已被使用");
	
//	PAY_RUN(220, "支付中"),
//	CANCEL_ORDER_FAIL(225, "取消订单失败"),
//	CANCEL_ORDER_NO(225, "不能取消预约"),
//
//	HOSCODE_EXIST(230, "医院编号已经存在"),
//	NUMBER_NO(240, "可预约号不足"),
//	TIME_NO(250, "当前时间不可以预约"),
//
//	SIGN_ERROR(300, "签名错误"),
//	HOSPITAL_OPEN(310, "医院未开通，暂时不能访问"),
//	HOSPITAL_LOCK(320, "医院被锁定，暂时不能访问");
	
	
	private Integer code;
	private String message;
	
	private ResultCodeEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}
}
