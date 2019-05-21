package com.telecom.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 该类用来作为Cookie的工具类
 * @author 唐佳威
 *
 */
public class CookieUtil {
	/**
	 * 保存指定的时间
	 * @param name 要保存的数据的key值
	 * @param value 要保存的数据的value值
	 * @param extime 要保存的时间
	 * @param response 通过response新增该Cookie
	 */
	public static void addCookie(String name,String value,int extime,HttpServletResponse response) {
		Cookie c = new Cookie(name, value);//new Cookie
		c.setMaxAge(extime);//保存extime长的时间
		response.addCookie(c);//在浏览器中新建该Cookie
	}
	/**
	 * 保存一周
	 * @param name 要保存的数据的key值
	 * @param value 要保存的数据的value值
	 * @param response 通过response新增该Cookie
	 */
	public static void addCookie(String name,String value,HttpServletResponse response) {
		Cookie c = new Cookie(name, value);//new Cookie
		c.setMaxAge(7*24*60*60);//保存一周
		response.addCookie(c);//在浏览器中新建该Cookie
	}
	/**
	 * 获得Cookie的值
	 * @param name 要获得的对应Cookie的key值
	 * @param request 浏览器的请求
	 * @return 存在该Cookie返回其值,不存在返回空串
	 */
	public static String getCookie(String name,HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();//获得所有的Cookies
		for (Cookie cookie : cookies) {//遍历出Cookies
			if(cookie.getName().equals(name)) {//将Cookies的名字和所传递的形参名进行比较,成功后进入if语句
				return cookie.getValue();//返回指定的Cookie中的value
			}
		}
		return "";
	}
	/**
	 * 删除Cookie
	 * @param name 要删除的Cookie的key值
	 * @param value 要删除的Cookie的value值
	 * @param response 服务器端请求
	 */
	public static void removeCookie(String name,String value,HttpServletResponse response) {
		Cookie c = new Cookie(name, value);//new Cookie
		c.setMaxAge(0);//将时间设置为0,即可实现清除
		response.addCookie(c);//将浏览器中的Cookie清除
	}
}
