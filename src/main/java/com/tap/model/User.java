package com.tap.model;

import java.sql.Timestamp;

public class User {
	private int userid;
	private String name;
	private String username;
	private String password;
	private String email;
	private String phone;
	private String address;
	private String role;
	private Timestamp createdata;
	private Timestamp lastlogindate;
	
	User() {
		
	}

	public User(int userid, String name, String username, String password, String email, String phone, String address,
			String role, Timestamp createData, Timestamp lastLoginDate) {
		super();
		this.userid = userid;
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.role = role;
		this.createdata = createData;
		this.lastlogindate = lastLoginDate;
	}
	
	

	public User(String name, String username, String password, String email, String phone, String address, String role,
			Timestamp createdata, Timestamp lastlogindate) {
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.role = role;
		this.createdata = createdata;
		this.lastlogindate = lastlogindate;
	}

	public int getUserId() {
		return userid;
	}

	public void setUserId(int userid) {
		this.userid = userid;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Timestamp getCreateData() {
		return createdata;
	}

	public void setCreateData(Timestamp createdata) {
		this.createdata = createdata;
	}

	public Timestamp getLastLoginDate() {
		return lastlogindate;
	}

	public void setLastLoginDate(Timestamp lastlogindate) {
		this.lastlogindate = lastlogindate;
	}

	@Override
	public String toString() {
		return "User [userid=" + userid + ", name=" + name + ", username=" + username + ", password=" + password
				+ ", email=" + email + ", phone=" + phone + ", address=" + address + ", role=" + role + ", createdata="
				+ createdata + ", lastlogindate=" + lastlogindate + "]";
	}
	
	

}
