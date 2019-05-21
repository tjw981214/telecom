package com.telecom.utils;


import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 发送邮箱验证码
 * @author 唐佳威
 */
public class SendQQEMailUtil {
	
	public static void sendEmail(String to,String title,String content) throws AddressException,MessagingException {
		//创建连接对象
		Properties properties = new Properties();
		try {
			properties.load(SendQQEMailUtil.class.getClassLoader().getResourceAsStream("email.properties"));
			Session session = Session.getInstance(properties,new Authenticator() {
				public PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("792238520@qq.com", "ierqjvyymiygbahi");
				}
			});
			//创建邮箱对象
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("792238520@qq.com"));
			message.setRecipient(RecipientType.TO, new InternetAddress(to));
			message.setRecipient(RecipientType.CC, new InternetAddress("792238520@qq.com"));
			message.setSubject(""+title+"");
			message.setContent(""+content+"", "text/html;charset=utf-8");
			//发送邮件
			Transport.send(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
