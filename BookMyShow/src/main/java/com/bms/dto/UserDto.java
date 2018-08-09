package com.bms.dto;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class UserDto {
	
	private Integer userid;
	
	@NotNull
	@Email(message="Enter Unique Email Id Ex: abc@xyz.pqr")
	@Column(name = "username")
	private String username;
	
	@NotNull(message="Mobile No. cannot be Blank")
	private String mobileno;
	
	@NotNull(message="Password cannot be Blank")
	private String password;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
}
