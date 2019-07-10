package com.AIfntech.service.pojo;


public class User {

	private String username;
	private String password;
	private String status;
	private String tpassword;
	private String email;
	

	public User() {}
	public User(String username, String password, String status) {
		this.username = username;
		this.password = password;
		this.status = status;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTpassword() {
		return tpassword;
	}
	public void setTpassword(String tpassword) {
		this.tpassword = tpassword;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", status=" + status + ", tpassword="
				+ tpassword + ", email=" + email + "]";
	}

}
