package com.hssj.service;

import javax.mail.SendFailedException;

import com.hssj.pojo.User;

public interface UserService {
	/**
	 * 注册操作
	 * 
	 * @param user
	 * @return
	 * @throws SendFailedException 
	 */
	boolean register(User user);

	/**
	 * 激活账号
	 * 
	 * @param code
	 * @return
	 */
	boolean active(String code);

	/**
	 * 登录操作
	 * @param user
	 * @return
	 */
	User login(User user);

}
