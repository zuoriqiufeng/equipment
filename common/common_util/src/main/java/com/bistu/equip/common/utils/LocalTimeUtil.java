package com.bistu.equip.common.utils;

import com.google.common.annotations.VisibleForTesting;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Dx666
 * @Description
 * @Date 2021/8/8 - 15:03
 */
public class LocalTimeUtil {
	
	public static void getLocalTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = sdf.format(new Date());
		Date  date = new Date();
		System.out.println(date);
		System.out.println(dateStr);

	}
}
