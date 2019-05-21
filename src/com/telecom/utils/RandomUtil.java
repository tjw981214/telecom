package com.telecom.utils;

import java.util.Random;

/**
 * 该类是为了产生随机验证码的类
 * @author 唐佳威
 *
 */
public class RandomUtil {

	//产生随机的字符
	public static String random() {
		String code = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		return code.charAt(new Random().nextInt(61))+"";//返回该字符
	}
	
}
