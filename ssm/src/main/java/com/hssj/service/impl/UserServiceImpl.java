package com.hssj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hssj.mapper.UserMapper;
import com.hssj.pojo.User;
import com.hssj.service.UserService;
import com.hssj.util.MailUtils;
import com.hssj.util.UniqueIdUtils;

@Service("userService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper mapper;

	@Override
	public boolean register(User user) {
		// dao中根据用户名查找判断该用户名是否已经存在
		User u = mapper.findByUsername(user.getUsername());
		// 判断语句 存在
		if (u == null) {
			// 返回该用户名不存在可以注册并存储到数据库中
			// 设置该用户的状态码
			user.setStatus("N");
			user.setCode(UniqueIdUtils.getUniqueid());
			mapper.save(user);
			// 发送邮件
			// 邮件正文
			String content = "<!DOCTYPE html>点击<a href='http://localhost:8080/ssm/activeUser?code=" + user.getCode()
					+ "'>激活</a>【亨盛世家】";
			MailUtils.sendMail(user.getEmail(), content, "亨盛世家账号激活");
			return true;
		} else {
			// 返回已经存在不可以注册
			return false;
		}
	}

	@Override
	public boolean active(String code) {
		// 根据激活码查询是否存在该用户
		User u = mapper.findByCode(code);
		if (u != null) {
			// 存在该用户，并修改该用户的激活状态
			mapper.updateStatus(u);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public User login(User user) {
		// 登录操作
		return mapper.findByUsernameAndPassword(user);
	}

}
