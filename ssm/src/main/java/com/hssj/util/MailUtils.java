package com.hssj.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtils {
	private static final String USER = "chengbox1@163.com";// 定义发件人的账户，同邮箱地址
	private static final String PASSWORD = "PYLBUXHVGTVUTQEO";// 定义发件人账户密码,或者授权码

	/**
	 * 
	 * @param to    收件人邮箱
	 * @param text  邮件正文
	 * @param title 邮件标题
	 * @return 发送验证信息的邮件
	 */
	public static boolean sendMail(String to, String text, String title) {
		try {
			final Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.host", "smtp.163.com");
			// 发件人的账号
			props.put("mail.user", USER);
			props.put("mail.password", PASSWORD);

			// 构建授权信息，进行SMTP授权验证
			Authenticator authenticator = new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					// 用户名密码
					String userName = props.getProperty("mail.user");
					String password = props.getProperty("mail.password");
					return new PasswordAuthentication(userName, password);
				}
			};
			// 使用环境属性和授权信息，创建邮件会话。
			Session mailSession = Session.getInstance(props, authenticator);
			// 创建邮件消息
			MimeMessage mimeMessage = new MimeMessage(mailSession);
			// 设置发件人
			String username = props.getProperty("mail.user");
			InternetAddress form = new InternetAddress(username);
			mimeMessage.setFrom(form);
			// 设置收件人
			InternetAddress toAddress = new InternetAddress(to);
			mimeMessage.setRecipient(Message.RecipientType.TO, toAddress);
			// 设置标题
			mimeMessage.setSubject(title);
			// 设置邮件的内容体
			mimeMessage.setContent(text, "text/html;charset=UTF-8");
			Transport.send(mimeMessage);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public static void main(String[] args) {
		MailUtils.sendMail("1928276656@qq.com", "这是一封测试邮件无需回复。", "测试邮件");
		System.out.println("发送成功！");
	}
}
