package com.hssj.mapper;

import com.hssj.pojo.User;

public interface UserMapper {
	/**
	 * 根据用户名查找
	 * @param user
	 * @return
	 */
	public User findByUsername(String username);
	
	/**
	 * 保存至数据库
	 * @param user
	 */
	public void save(User user);

	/**
	 * 根据激活码查找用户
	 * @param code
	 * @return
	 */
	public User findByCode(String code);

	/**
	 * 更新用户激活状态
	 * @param u
	 */
	public void updateStatus(User u);

	/**
	 * 根据用户名和密码查询
	 * @param user
	 * @return
	 */
	public User findByUsernameAndPassword(User user);
}
