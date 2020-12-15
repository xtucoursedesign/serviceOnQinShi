package org.bean;

import java.io.Serializable;

public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String uid;
	private String username;
	private String password;
	private String name;
	private String phone;
	private String faid;
	private String department;
	
	public User() {
		super();
	}

	public User(String uid, String username, String password, String name, String phone, String faid,
			String department) {
		super();
		this.uid = uid;
		this.username = username;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.faid = faid;
		this.department = department;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFaid() {
		return faid;
	}

	public void setFaid(String faid) {
		this.faid = faid;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", username=" + username + ", password=" + password + ", name=" + name + ", phone="
				+ phone + ", faid=" + faid + ", department=" + department + "]";
	}
}
