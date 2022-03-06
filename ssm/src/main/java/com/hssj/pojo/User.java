package com.hssj.pojo;

public class User {
	private int uid;
	private String username;
	private String password;
	private String email;
	private String telephone;
	private String sex;
	private String birthday;
	private String status; // 激活状态Y代表已激活，N代表未激活
	private String code;// 激活码（唯一）

	public User() {
	}

	public User(int uid, String username, String password, String email, String telephone, String sex, String birthday,
			String status, String code) {
		super();
		this.uid = uid;
		this.username = username;
		this.password = password;
		this.email = email;
		this.telephone = telephone;
		this.sex = sex;
		this.birthday = birthday;
		this.status = status;
		this.code = code;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {

		this.birthday = birthday;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", telephone=" + telephone + ", sex=" + sex + ", birthday=" + birthday + ", status=" + status
				+ ", code=" + code + "]";
	}

}
